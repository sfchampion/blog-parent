package com.mszlu.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mszlu.blog.admin.mapper.AdminMapper;
import com.mszlu.blog.admin.pojo.Admin;
import com.mszlu.blog.admin.pojo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sfChampion
 * @date 2023/2/10 15:33
 */

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public Admin findAdminByUserName(String username) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username);
        queryWrapper.last("limit 1");
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    public List<Permission> findPermissionByAdminId(Long id) {
        // //SELECT * FROM `ms_permission` where id in (select permission_id from ms_admin_permission where admin_id=1)
        return adminMapper.findPermissionByAdminId(id);
    }
}
