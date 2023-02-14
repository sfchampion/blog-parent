package com.mszlu.blog.controller;

import com.mszlu.blog.service.SysUserService;
import com.mszlu.blog.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/8 15:56
 */

@RestController
@RequestMapping("users")
public class UsersController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        return sysUserService.findUserByToken(token);
    }
}
