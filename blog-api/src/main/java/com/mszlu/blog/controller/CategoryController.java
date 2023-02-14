package com.mszlu.blog.controller;

import com.mszlu.blog.service.CategoryService;
import com.mszlu.blog.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/9 15:45
 */

@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping
    public Result listCategory() {
        return categoryService.findAll();
    }

    @GetMapping("detail")
    public Result detailCategory() {
        return categoryService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result categoriesDetailById(@PathVariable("id") Long id){
        return categoryService.categoriesDetailById(id);
    }
}
