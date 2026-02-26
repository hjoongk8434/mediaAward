package ma.web.controllers;

import ma.web.dto.AddSearchListDTO;
import ma.web.dto.MultiSearchResultDTO;
import ma.web.services.SearchService;
import ma.web.vo.SearchServiceResultVO;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public SearchService searchService;

    @RequestMapping(value = "/search/main", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try{
            log.info("[SearchController.search(HttpServletRequest request) is enter]");
            String keyword = request.getParameter("keyword");
            String page = request.getParameter("page") != null && !"".equals(request.getParameter("page")) ? request.getParameter("page") : "1";
            log.info("INFO keyword ::: [{}]", keyword);
            SearchServiceResultVO searchServiceResultVO =  searchService.searchResult(keyword, page);
            mav.addObject("searchResult", searchServiceResultVO);
            mav.addObject("keyword", keyword);
            mav.addObject("imgBaseUrl", searchServiceResultVO.getImgUrl());
        } catch (Exception e) {
            log.error("Error [SearchController] ::: [{}]", e.getMessage());
            e.printStackTrace();
        }
        mav.setViewName("search/main");
        return mav;
    }

    @RequestMapping(value = "/search/addList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addSearchList(HttpServletRequest request, @RequestParam Map<String, String> params) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<MultiSearchResultDTO> results = new ArrayList<MultiSearchResultDTO>();
        try{
            log.info("[SearchController.addSearchList(HttpServletRequest request, @RequestParam Map<String, String> params) is enter]");
            String keyword = params.get("keyword");
            String page = params.get("page");
            log.info("INFO keyword ::: [{}]", keyword);
            SearchServiceResultVO searchServiceResultVO = searchService.searchResult(keyword, page);
            List<AddSearchListDTO> addSearchDataConverterProcess = searchService.addSearchDataConverterProcess(searchServiceResultVO);
            resultMap.put("data", addSearchDataConverterProcess);
        } catch (Exception e) {
            log.error("Error [SearchController] ::: [{}]", e.getMessage());
            e.printStackTrace();
        }

        return resultMap;
    }
}
