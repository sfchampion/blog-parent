package com.mszlu.blog.dao.pojo;

import lombok.Data;


/**
 * @author sfChampion
 */
@Data
public class Comment {

    private Long id;

    private String content;

    private String createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
