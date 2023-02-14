package com.mszlu.blog.controller;

import com.mszlu.blog.service.CommentsService;
import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.CommentParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sfChampion
 * @date 2023/2/9 14:18
 */

@RestController
@RequestMapping("comments")
public class CommentsController {

    @Resource
    private CommentsService commentsService;

    @GetMapping("article/{id}")
    public Result comments(@PathVariable("id") Long id) {
        return commentsService.commentsByArticleId(id);
    }

    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentsService.comment(commentParam);
    }
}
