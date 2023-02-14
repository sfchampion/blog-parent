package com.mszlu.blog.controller;

import com.mszlu.blog.service.LoginService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/8 14:25
 */

@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam) {
        // 登录 验证用户 访问用户表
        return loginService.login(loginParam);
    }
}
