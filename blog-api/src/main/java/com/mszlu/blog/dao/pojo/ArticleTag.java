package com.mszlu.blog.dao.pojo;

import lombok.Data;

/**
 * @author sfChampion
 */
@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}