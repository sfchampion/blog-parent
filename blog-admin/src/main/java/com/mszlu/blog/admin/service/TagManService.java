package com.mszlu.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blog.admin.mapper.TagManMapper;
import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.pojo.Tag;
import com.mszlu.blog.admin.vo.PageResult;
import com.mszlu.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/23 13:17
 */

@Service
public class TagManService {

    @Resource
    private TagManMapper tagManMapper;

    public Result tagManList(PageParam pageParam) {
        Page<Tag> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.like(Tag::getTagName,pageParam.getQueryString());
        }
        Page<Tag> tagPage = tagManMapper.selectPage(page, queryWrapper);
        PageResult<Tag> pageResult = new PageResult<>();
        pageResult.setList(tagPage.getRecords());
        pageResult.setTotal(tagPage.getTotal());
        return Result.success(pageResult);
    }

    public Result tagDelete(Long id) {
        this.tagManMapper.deleteById(id);
        return Result.success(null);
    }
}
