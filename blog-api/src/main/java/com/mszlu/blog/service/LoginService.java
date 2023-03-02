package com.mszlu.blog.service;

import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.vo.LoginVo;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.LoginParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author sfChampion
 * @date 2023/2/8 14:28
 */
public interface LoginService {

    /**
     * 登录功能
     * @param loginParam
     * @param session
     * @return
     */
    Result login(LoginParam loginParam, HttpSession session);

    /**
     * 检查token
     * @param token
     * @return
     */
    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);

    /**
     * 手机登录
     * @param loginVo
     * @return
     */
    Map<String, Object> phoneLoginUser(LoginVo loginVo);
}
