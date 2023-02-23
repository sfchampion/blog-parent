package com.mszlu.blog.admin.controller;

import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.service.CategoryManService;
import com.mszlu.blog.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/23 13:03
 */

@RestController
@RequestMapping("category")
public class CategoryManController {

    @Resource
    private CategoryManService categoryManService;

    @PostMapping("categoryManList")
    public Result categoryManList(@RequestBody PageParam pageParam) {
        return categoryManService.CategoryManList(pageParam);
    }

    @GetMapping("categoryDelete/{id}")
    public Result categoryDelete(@PathVariable("id") Long id) {
        return categoryManService.categoryDelete(id);
    }
}
