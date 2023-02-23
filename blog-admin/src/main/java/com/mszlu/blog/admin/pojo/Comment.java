package com.mszlu.blog.admin.pojo;

import lombok.Data;

/**
 * @author sfChampion
 */
@Data
public class Comment {

    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
