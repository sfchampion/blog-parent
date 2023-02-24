package com.mszlu.blog.admin.pojo;

import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/1/31 11:28
 */

@Data
public class Article {
    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private String id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    /**
     * 作者id
     */
    private String authorId;
    /**
     * 内容id
     */
    private String bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private Integer weight;

    /**
     * 创建时间
     */
    private Long createDate;
}
