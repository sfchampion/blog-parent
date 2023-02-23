package com.mszlu.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mszlu.blog.admin.mapper.CategoryManMapper;
import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.pojo.Category;
import com.mszlu.blog.admin.vo.PageResult;
import com.mszlu.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/23 13:09
 */

@Service
public class CategoryManService {

    @Resource
    private CategoryManMapper categoryManMapper;


    public Result CategoryManList(PageParam pageParam) {
        Page<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper.like(Category::getCategoryName,pageParam.getQueryString());
        }
        Page<Category> categoryPage = categoryManMapper.selectPage(page, queryWrapper);
        PageResult<Category> pageResult = new PageResult<>();
        pageResult.setList(categoryPage.getRecords());
        pageResult.setTotal(categoryPage.getTotal());
        return Result.success(pageResult);
    }


    public Result categoryDelete(Long id) {
        this.categoryManMapper.deleteById(id);
        return Result.success(null);
    }
}
