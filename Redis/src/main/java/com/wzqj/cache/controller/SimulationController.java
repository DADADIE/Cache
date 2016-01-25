package com.wzqj.cache.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzqj.cache.service.MessageRedisService;
import com.wzqj.compoent.config.ConfigHelper;
import com.wzqj.compoent.http.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanjunpu on 2016/1/25.
 */
@Controller
@RequestMapping("simulation")
public class SimulationController {

    private static Logger logger = LogManager.getLogger(SimulationController.class);

    @RequestMapping("phone")
    public ModelAndView phone(String memberId){
        ModelAndView mv = new ModelAndView();
        mv.addObject("memberId",memberId);
        mv.setViewName("phone");
        return mv;
    }
    @RequestMapping("POS")
    public String POS(){
        return "POS";
    }
}
