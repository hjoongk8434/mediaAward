package ma.web.controllers;

import ma.web.services.HomeService;
import ma.web.vo.HomeServiceResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    private Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public HomeService homeService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        try {
            log.info("[HomeController.home() is enter]");
            HomeServiceResultVO resultVO = homeService.popularService();
            mv.addObject("result", resultVO);
            mv.addObject("imgBaseUrl", resultVO.getImgUrl());
            mv.setViewName("home");
        }catch (Exception e){
            log.error("Error [HomeController] ::: [{}]", e.getMessage());
            e.printStackTrace();
        }
        return mv;
    }
}
