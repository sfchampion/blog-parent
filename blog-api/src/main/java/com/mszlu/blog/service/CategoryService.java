package com.mszlu.blog.service;

import com.mszlu.blog.vo.CategoryVo;
import com.mszlu.blog.vo.Result;

import java.util.List;

/**
 * @author sfChampion
 * @date 2023/2/9 11:36
 */
public interface CategoryService {

    /**
     * 查询类别
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    /**
     * 查询所有类别
     * @return
     */
    Result findAll();

    /**
     * 查询类别详情
     * @return
     */
    Result findAllDetail();

    /**
     * 分类文章列表
     * @param id
     * @return
     */
    Result categoriesDetailById(Long id);
}
