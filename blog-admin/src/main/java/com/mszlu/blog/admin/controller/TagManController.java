package com.mszlu.blog.admin.controller;

import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.service.TagManService;
import com.mszlu.blog.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/23 13:03
 */

@RestController
@RequestMapping("tags")
public class TagManController {

    @Resource
    private TagManService tagManService;

    @PostMapping("tagManList")
    public Result tagManList(@RequestBody PageParam pageParam) {
        return tagManService.tagManList(pageParam);
    }

    @GetMapping("tagDelete/{id}")
    public Result tagDelete(@PathVariable("id") Long id) {
        return tagManService.tagDelete(id);
    }
}
