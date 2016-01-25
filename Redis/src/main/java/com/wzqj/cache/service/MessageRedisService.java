package com.wzqj.cache.service;

/**
 * Created by guanjunpu on 2016/1/25.
 */
public interface MessageRedisService {
    /**
     *设置会员推送消息对应的sessionId
     * @param memberId
     * @param seconds
     * @param sessionId
     */
    void setMemberSessionId(String memberId, int seconds, String sessionId);

    /**
     *获取会员推送消息对应的sessionId
     * @param memberId
     * @return
     */
    String getMemberSessionId(String memberId);
}
