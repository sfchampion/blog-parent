package com.mszlu.blog.controller;

import com.mszlu.blog.service.LoginService;
import com.mszlu.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/8 16:59
 */

@RestController
@RequestMapping("logout")
public class LogoutController {

    @Resource
    private LoginService loginService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }
}
