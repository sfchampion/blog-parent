package com.mszlu.blog.admin.pojo;

import lombok.Data;

/**
 * @author sfChampion
 */
@Data
public class Comment {

    private String id;

    private String content;

    private String createDate;

    private String articleId;

    private String authorId;

    private String parentId;

    private String toUid;

    private Integer level;
}
