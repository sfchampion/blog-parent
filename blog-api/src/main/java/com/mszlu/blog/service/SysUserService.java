package com.mszlu.blog.service;

import com.mszlu.blog.dao.pojo.SysUser;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.UserVo;

/**
 * @author sfChampion
 * @date 2023/1/31 14:33
 */
public interface SysUserService {

    /**
     * 根据id查找用户vo
     * @param id
     * @return
     */
    UserVo findUserVoById(Long id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    SysUser findUserById(Long id);

    /**
     * 查找用户
     * @param account
     * @param password
     * @return
     */
    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据用户名查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
