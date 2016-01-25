package com.wzqj.cache.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzqj.cache.service.MessageRedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guanjunpu on 2016/1/25.
 */
@Controller
@RequestMapping("redis")
public class MemberSessionIdRedisController {

    private static Logger logger = LogManager.getLogger(MemberSessionIdRedisController.class);
    private static final int DEFAULT_CACHE_ALIVE_TIME= 7200;
    @Autowired
    private MessageRedisService messageRedisService;

    @RequestMapping("setMemberSessionId")
    @ResponseBody
    public String setMemberSessionId(String memberId,String sessionId){
        JSONObject output = new JSONObject();
        messageRedisService.setMemberSessionId(memberId,DEFAULT_CACHE_ALIVE_TIME,sessionId);
        return output.toJSONString();
    }
    @RequestMapping("getMemberSessionId")
    @ResponseBody
    public String getMemberSessionId(String memberId){
        return messageRedisService.getMemberSessionId(memberId);
    }
}
