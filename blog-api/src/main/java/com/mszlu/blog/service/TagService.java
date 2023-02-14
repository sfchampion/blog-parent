package com.mszlu.blog.service;

import com.mszlu.blog.vo.Result;
import com.mszlu.blog.vo.TagVo;

import java.util.List;

/**
 * @author sfChampion
 * @date 2023/1/31 14:02
 */
public interface TagService {
    /**
     * 文章列表
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 最热标签
     * @param limit
     * @return
     */
    Result hots(int limit);

    /**
     * 查询所有的文章标签
     * @return
     */
    Result findAll();

    /**
     * 查询所有的文章标签
     * @return
     */
    Result findAllDetail();

    /**
     * 标签文章列表
     * @param id
     * @return
     */
    Result findDetailById(Long id);
}
