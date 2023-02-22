package com.mszlu.blog.service;

/**
 * @author sfChampion
 * @date 2023/2/17 17:21
 */
public interface MsmService {
    /**
     * 发送手机验证码
     * @param phone
     * @param sixBitRandom
     * @return
     */
    boolean send(String phone, String sixBitRandom);
}
