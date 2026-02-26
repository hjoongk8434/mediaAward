package ma.web.services;

import ma.web.common.CustomTdRestTemplate;
import ma.web.common.UtilProcessClass;
import ma.web.dto.AddSearchListDTO;
import ma.web.dto.MultiSearchDTO;
import ma.web.dto.MultiSearchResultDTO;
import ma.web.vo.HomeServiceResultVO;
import ma.web.vo.SearchServiceResultVO;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    private Logger log = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private CustomTdRestTemplate customTdRestTemplate;

    @Autowired
    private Environment env;

    public SearchServiceResultVO searchResult(String keyword, String page) {
        SearchServiceResultVO resultVO = new SearchServiceResultVO();
        Map<String, String> params = new HashMap<String, String>();
        params.put("query", keyword);
        params.put("include_adult", "false");
        params.put("language", "ko-KR");
        params.put("page", page);

        String paramsProcess = UtilProcessClass.convertMapToGetparams(params);
        log.info("INFO paramsProcess ::: [{}]", paramsProcess);
        try{

            ResponseEntity<HashMap> tmdbAuth = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.auth"), HashMap.class, null);
            boolean authenticated = Boolean.parseBoolean(tmdbAuth.getBody().get("success").toString());
            log.info("$$$ authenticated : {}", authenticated);

            if(authenticated){
                resultVO.setAuthenticated(authenticated);
                resultVO.setImgUrl(env.getProperty("td.url.base.img") + env.getProperty("td.url.w300.img"));
                ResponseEntity<MultiSearchDTO> multiSearchData = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.search.multi"), MultiSearchDTO.class, paramsProcess);

                if(HttpStatus.OK.value() == multiSearchData.getStatusCodeValue() && multiSearchData.getBody().getResults().size() > 0) {
                    resultVO.setHasSearchData(true);
                    resultVO.setMultiSearchData(multiSearchData.getBody());
                    log.info("$$$ multiSearchData : {}", multiSearchData);
                    log.info("$$$ multiSearchData.getBody().getTotal_pages() : {}", multiSearchData.getBody().getTotal_pages());
                }
            }
        }catch (Exception e){
            log.error("Error [SearchService] ::: [{}]", e.getMessage());
            e.printStackTrace();
        }
        return resultVO;
    }

    public List<AddSearchListDTO> addSearchDataConverterProcess (SearchServiceResultVO searchServiceResultVO) {
        List<AddSearchListDTO> resultVO = new ArrayList<AddSearchListDTO>();

        for(MultiSearchResultDTO multiSearchResultDTO : searchServiceResultVO.getMultiSearchData().getResults()) {
            AddSearchListDTO addSearchListDTO = new AddSearchListDTO();
            addSearchListDTO.setImgUrl(searchServiceResultVO.getImgUrl() + multiSearchResultDTO.getPoster_path() );
            addSearchListDTO.setTitle(multiSearchResultDTO.getTitle());
            addSearchListDTO.setAverage(String.format("%.1f", multiSearchResultDTO.getVote_average()));
            addSearchListDTO.setType("tv".equals(multiSearchResultDTO.getMedia_type()) ? "드라마": "영화");
            addSearchListDTO.setOverview(multiSearchResultDTO.getOverview());
            resultVO.add(addSearchListDTO);
        }

        return resultVO;
    }
}
