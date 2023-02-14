package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.params.CommentParam;

/**
 * @author sfChampion
 * @date 2023/2/9 14:22
 */
public interface CommentsService {

    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    /**
     * 评论
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
