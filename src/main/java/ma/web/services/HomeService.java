package ma.web.services;

import ma.web.common.CustomTdRestTemplate;
import ma.web.dto.MoviePopularDTO;
import ma.web.dto.TvPopularDTO;
import ma.web.common.UtilProcessClass;

import ma.web.vo.HomeServiceResultVO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HomeService {
    private Logger log = LoggerFactory.getLogger(HomeService.class);

    @Autowired
    private Environment env;

    @Autowired
    public CustomTdRestTemplate customTdRestTemplate = new CustomTdRestTemplate();


    public HomeServiceResultVO popularService(){
        HomeServiceResultVO resultVO = new HomeServiceResultVO();
        Map<String, String> params = new HashMap<String, String>();
        params.put("language", "ko-KR");
        params.put("page", "1");

        String paramsProcess = UtilProcessClass.convertMapToGetparams(params);

        try{
            ResponseEntity<HashMap> tmdbAuth = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.auth"), HashMap.class, null);
            boolean authenticated = Boolean.parseBoolean(tmdbAuth.getBody().get("success").toString());
            log.info("$$$ authenticated : {}", authenticated);

            if(authenticated){
                resultVO.setAuthenticated(authenticated);
                resultVO.setImgUrl(env.getProperty("td.url.base.img") + env.getProperty("td.url.w300.img"));
                ResponseEntity<MoviePopularDTO> tdMoviePopularData = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.popular.movie"), MoviePopularDTO.class, paramsProcess);
                log.info("$$$ tdMoviePopularData.getBody() : {}", tdMoviePopularData.getBody());
                log.info("$$$ tdMoviePopularData.getStatusCode() : {}", tdMoviePopularData.getStatusCode());
                log.info("$$$ tdMoviePopularData.getStatusCodeValue() : {}", tdMoviePopularData.getStatusCodeValue());

                resultVO.setHasTmdbMovieData(true);
                resultVO.setMoviePopularData(tdMoviePopularData.getBody());
                if(HttpStatus.OK.value() == tdMoviePopularData.getStatusCodeValue() && tdMoviePopularData.getBody().getResults().size() > 0) {
                    ResponseEntity<TvPopularDTO> tdTvPopularData = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.popular.tv"), TvPopularDTO.class, paramsProcess);
                    log.info("$$$ tdTvPopularData.getBody() : {}", tdTvPopularData.getBody());
                    log.info("$$$ tdTvPopularData.getStatusCode() : {}", tdTvPopularData.getStatusCode());
                    log.info("$$$ tdTvPopularData.getStatusCodeValue() : {}", tdTvPopularData.getStatusCodeValue());

                    if(HttpStatus.OK.value() == tdTvPopularData.getStatusCodeValue() && tdTvPopularData.getBody().getResults().size() > 0) {
                        resultVO.setHasTmdbTvData(true);
                        resultVO.setTvPopularData(tdTvPopularData.getBody());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error [HomeService] ::: [{}]", e.getMessage());
            e.printStackTrace();
        }

        return resultVO;
    }

//    public void moviePopularService() {
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("language", "ko-KR");
//        params.put("page", "1");
//
//        ResponseEntity<MoviePopularDTO> tdMoviePopularData = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.popular.movie"), MoviePopularDTO.class, params);
//
//        log.info("$$$ tdMoviePopularData : {}", tdMoviePopularData);
//
//        //return null;
//    }
//
//    public void tvPopularService() {
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("language", "ko-KR");
//        params.put("page", "1");
//
//        ResponseEntity<TvPopularDTO> tdTvPopularData = customTdRestTemplate.tdConnectionForEntity(env.getProperty("td.url.popular.tv"), TvPopularDTO.class, params);
//
//        log.info("$$$ tdTvPopularData : {}", tdTvPopularData);
//
//        //return null;
//    }
}
