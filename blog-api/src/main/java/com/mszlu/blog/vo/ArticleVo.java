package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author sfChampion
 * @date 2023/1/31 12:14
 */

@Data
public class ArticleVo {
    /**
     * 文章id，防止出现精度损失
     */
    //@JsonSerialize(using = ToStringSerializer.class)
    //private Long id;
    private String id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private UserVo author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo category;
}
