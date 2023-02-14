package com.mszlu.blog.admin.controller;

import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.pojo.Permission;
import com.mszlu.blog.admin.service.PermissionService;
import com.mszlu.blog.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/10 14:35
 */

@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("permission/permissionList")
    public Result permissionList(@RequestBody PageParam pageParam) {
        return permissionService.listPermissions(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }


}
