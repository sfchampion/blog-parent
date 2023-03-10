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
 * @date 2023/2/8 17:08
 */

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }
}
