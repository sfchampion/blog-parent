package com.mszlu.blog.admin.controller;

import com.mszlu.blog.admin.model.params.PageParam;
import com.mszlu.blog.admin.service.CommentManService;
import com.mszlu.blog.admin.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/22 17:01
 */

@RestController
@RequestMapping("comments")
public class CommentManController {

    @Resource
    private CommentManService commentManService;

    @PostMapping("commentList")
    public Result commentList(@RequestBody PageParam pageParam){
        return commentManService.listComment(pageParam);
    }

    @GetMapping("commentDelete/{id}")
    public Result commentDetail(@PathVariable("id") Long id){
        return commentManService.commentDetail(id);
    }

}
