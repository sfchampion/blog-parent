package com.mszlu.blog.admin.controller;

import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.service.ArticleManService;
import com.mszlu.blog.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/22 17:00
 */

@RestController
@RequestMapping("/articles")
public class ArticleManController {

    @Resource
    private ArticleManService articleManService;

    @PostMapping("/articleList")
    public Result articleList(@RequestBody PageParam pageParam){
        return articleManService.articleList(pageParam);
    }

    @GetMapping("/articleDelete/{id}")
    public Result articleDetail(@PathVariable("id") Long id){
        return articleManService.deleteArticle(id);
    }
}
