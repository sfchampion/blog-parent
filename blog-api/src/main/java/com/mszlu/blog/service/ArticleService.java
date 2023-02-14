package com.mszlu.blog.service;

import com.mszlu.blog.vo.params.ArticleParam;
import com.mszlu.blog.vo.params.PageParams;
import com.mszlu.blog.vo.Result;

/**
 * @author sfChampion
 * @date 2023/1/31 11:49
 */
public interface ArticleService {
    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);


    /**
     * 文章搜索
     * @param search
     * @return
     */
    Result searchArticle(String search);
}
