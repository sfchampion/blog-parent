package com.mszlu.blog.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mszlu.blog.service.MsmService;
import com.mszlu.blog.utils.ConstantPropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

/**
 * @author sfChampion
 * @date 2023/2/17 17:21
 */

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String phone, String sixBitRandom) {
        //判断手机号是否为空
        if (StringUtils.isEmpty(phone)){
            return false;
        }
        //发送，整合容联云
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);

        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        sdk.setAccount(ConstantPropertiesUtils.ACCOUNT_SID, ConstantPropertiesUtils.ACCOUNT_TOKEN);
        //请使用管理控制台中已创建应用的APPID
        sdk.setAppId(ConstantPropertiesUtils.APP_ID);
        sdk.setBodyType(BodyType.Type_JSON);
        String to = "18729181871";
        String templateId= "1";
        //传入验证码
        String[] datas = {sixBitRandom,"10"};

        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
            //成功
            return true;
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            //失败
            return false;
        }
    }
}
