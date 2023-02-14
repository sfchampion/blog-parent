package com.mszlu.blog.admin.service;

import com.mszlu.blog.admin.pojo.Admin;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author sfChampion
 * @date 2023/2/10 15:29
 */

@Component
public class SecurityUserService implements UserDetailsService {

    @Resource
    private AdminService adminService;

    /**
     * 登录认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
        * 登录的时候，会把username传递到这里
        * 通过username查询 admin表，如果admin存在，将密码告诉 spring security
        * 如果不存在，返回null，认证失败
        * */
        Admin admin = this.adminService.findAdminByUserName(username);
        if (admin == null) {
            // 登录失败
            return null;
        }
        UserDetails userDetails = new User(username,admin.getPassword(),new ArrayList<>());
        return userDetails;
    }
}
