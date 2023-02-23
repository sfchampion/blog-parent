package com.mszlu.blog.admin.controller;

import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.service.UserManService;
import com.mszlu.blog.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/22 15:29
 */

@RestController
@RequestMapping("userMan")
public class UserManController {

    @Resource
    private UserManService userManService;

    @PostMapping("userList")
    public Result permissionList(@RequestBody PageParam pageParam) {
        return userManService.listUserMan(pageParam);
    }

    @GetMapping("delete/{id}")
    public Result deleteUser(@PathVariable("id") Long id) {
        return userManService.deleteUser(id);
    }
}
