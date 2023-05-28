package com.mszlu.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blog.admin.mapper.UserManMapper;
import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.pojo.Manager;
import com.mszlu.blog.admin.pojo.Permission;
import com.mszlu.blog.admin.pojo.SysUser;
import com.mszlu.blog.admin.vo.PageResult;
import com.mszlu.blog.admin.vo.Result;
import com.mszlu.blog.admin.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/22 15:37
 */

@Service
public class UserManService {

    @Resource
    private UserManMapper userManMapper;

    public Result listUserMan(PageParam pageParam) {
        /*
         * 要的数据，管理台，表的所有的字段 Permission
         * 分页查询
         * */
        Page<Manager> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Manager> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.like(Manager::getUserId,pageParam.getQueryString());
        }
        Page<Manager> userManPage = userManMapper.selectPage(page, queryWrapper);
        PageResult<Manager> pageResult = new PageResult<>();
        pageResult.setList(userManPage.getRecords());
        pageResult.setTotal(userManPage.getTotal());
        return Result.success(pageResult);
    }

    public Result deleteUser(Long id) {
        this.userManMapper.deleteById(id);
        return Result.success(null);
    }

    public Result updateUser(Manager Manager) {
        this.userManMapper.updateById(Manager);
        return Result.success(null);
    }
}
