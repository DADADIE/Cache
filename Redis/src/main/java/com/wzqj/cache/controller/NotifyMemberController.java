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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanjunpu on 2016/1/25.
 */
@Controller
@RequestMapping("notifyMember")
public class NotifyMemberController {

    private static Logger logger = LogManager.getLogger(NotifyMemberController.class);
    @Autowired
    private MessageRedisService messageRedisService;

    @RequestMapping("updateBalance")
    @ResponseBody
    public String updateBalance(String memberId,String balance){
        JSONObject output = new JSONObject();
        try{
            String sessionId = messageRedisService.getMemberSessionId(memberId);
            logger.debug("sessionId:"+sessionId);
            if(null!=sessionId){
                JSONObject notifyData = new JSONObject();
                notifyData.put("balance",balance);
                synWebMsg(sessionId,0,"充值成功.",notifyData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output.toJSONString();
    }

    private void synWebMsg(String sessionId, Integer status, String info, Object data) throws Exception {
        Map<String, Object> hdata = new HashMap<String, Object>();
        hdata.put("status", status);
        hdata.put("info", info);
        hdata.put("data", data);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sessionId", sessionId);
        param.put("data", hdata);
        sendWebSocketData(param);
    }

    private String sendWebSocketData(Map<String, Object> param) throws Exception {
        String webSocketUrl = ConfigHelper.getProperty("WEBSOCKET_URL");
        logger.debug("sendWebSocketData,webSocketUrl:" + webSocketUrl + param);
        String str = HttpUtil.requestPostJSON(webSocketUrl, param);
        logger.debug("sendWebSocketData,return" + str);
        return str;
    }
}
