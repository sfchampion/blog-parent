package com.mszlu.blog.admin.service;

import com.mszlu.blog.admin.pojo.Admin;
import com.mszlu.blog.admin.pojo.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sfChampion
 * @date 2023/2/10 15:42
 */
@Service
public class AuthService {

    @Resource
    private AdminService adminService;

    public boolean auth(HttpServletRequest request, Authentication authentication) {
        /*
        * 权限认证
        * */
        // 请求路径
        String requestURI = request.getRequestURI();
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)) {
            // 未登录
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin = adminService.findAdminByUserName(username);
        if (admin == null) {
            return false;
        }
        if (admin.getId() == 1) {
            // 超级管理员
            return true;
        }
        Long id = admin.getId();
        List<Permission> permissionList = this.adminService.findPermissionByAdminId(id);
        requestURI = StringUtils.split(requestURI, "?")[0];
        for (Permission permission : permissionList) {
            if (requestURI.equals(permission.getPath())) {
                return true;
            }
        }
        return false;
    }
}
