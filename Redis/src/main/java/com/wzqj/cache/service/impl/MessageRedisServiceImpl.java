package com.wzqj.cache.service.impl;

import com.wzqj.cache.service.MessageRedisService;
import com.wzqj.common.redis.CacheClient;
import com.wzqj.common.redis.CacheKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guanjunpu on 2016/1/25.
 */
@Service
public class MessageRedisServiceImpl implements MessageRedisService {

    private static Logger logger = LogManager.getLogger(MessageRedisServiceImpl.class);
    @Autowired
    private CacheClient cacheClient;

    public void setMemberSessionId(String memberId, int seconds, String sessionId){
        logger.debug("-----MessageRedisServiceImpl-----setMemberSessionId-----start-----");
        logger.debug("key:" + memberId);
        logger.debug("seconds:" + seconds);
        logger.debug("value:" + sessionId);
        cacheClient.setex(CacheKey.MEMBER_SESSIONID+memberId, seconds, sessionId);
        logger.debug("-----MessageRedisServiceImpl-----setMemberSessionId-----end-----");
    }

    public String getMemberSessionId(String memberId) {
        logger.debug("-----MessageRedisServiceImpl-----getMemberSessionId-----start-----");
        logger.debug("key:" + CacheKey.MEMBER_SESSIONID + memberId);
        String sessionId = (String) cacheClient.get(CacheKey.MEMBER_SESSIONID + memberId);
        logger.debug("value:" + sessionId);
        logger.debug("-----MessageRedisServiceImpl-----getMemberSessionId-----end-----");
        return sessionId;
    }
}
