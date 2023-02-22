package com.mszlu.blog.controller;

import com.mszlu.blog.service.MsmService;
import com.mszlu.blog.utils.RandomUtil;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author sfChampion
 * @Date 2022/4/17 21:03
 * @Version 2020.3.4
 */

@RestController
@RequestMapping("/msm")
public class MsmController {

    @Resource
    private MsmService msmService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果能获取到，返回ok
        //key 手机号  value  验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.success(null);
        }

        //如果从redis获取不到，
        //生成验证码，容联云
        String sixBitRandom = RandomUtil.getSixBitRandom();
        //调用service方法，整合短信服务进行发送
        boolean isSend = msmService.send(phone,sixBitRandom);
        //生成验证码放到redis里面，设置有效时间
        if (isSend){
            redisTemplate.opsForValue().set(phone,sixBitRandom,10, TimeUnit.MINUTES);
            return Result.success(null);
        } else {
            return Result.fail(ResultCode.SENDING_SMS_FAIL.getCode(), ResultCode.SENDING_SMS_FAIL.getMsg());
        }
    }
}
