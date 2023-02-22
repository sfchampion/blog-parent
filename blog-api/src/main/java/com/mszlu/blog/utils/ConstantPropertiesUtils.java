package com.mszlu.blog.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author sfChampion
 * @Date 2022/4/17 18:52
 * @Version 2020.3.4
 */

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${cloopen.sms.accountSId}")
    private String accountSId;
    @Value("${cloopen.sms.accountToken}")
    private String accountToken;
    @Value("${cloopen.sms.appId}")
    private String appId;

    public static String ACCOUNT_SID;
    public static String ACCOUNT_TOKEN;
    public static String APP_ID;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCOUNT_SID = accountSId;
        ACCOUNT_TOKEN =accountToken;
        APP_ID = appId;
    }

}
