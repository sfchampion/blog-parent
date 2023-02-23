package com.mszlu.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blog.admin.mapper.CommentMapper;
import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.pojo.Comment;
import com.mszlu.blog.admin.pojo.SysUser;
import com.mszlu.blog.admin.vo.PageResult;
import com.mszlu.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/22 17:05
 */

@Service
public class CommentManService {

    @Resource
    private CommentMapper commentMapper;

    public Result listComment(PageParam pageParam) {
        Page<Comment> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.like(Comment::getContent,pageParam.getQueryString());
        }
        Page<Comment> commentPage = commentMapper.selectPage(page, queryWrapper);
        PageResult<Comment> pageResult = new PageResult<>();
        pageResult.setList(commentPage.getRecords());
        pageResult.setTotal(commentPage.getTotal());
        return Result.success(pageResult);
    }

    public Result commentDetail(Long id) {
        this.commentMapper.deleteById(id);
        return Result.success(null);
    }
}
