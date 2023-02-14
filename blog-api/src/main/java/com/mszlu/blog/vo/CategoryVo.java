package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author sfChampion
 */
@Data
public class CategoryVo {

    private String id;

    private String avatar;

    private String categoryName;

    private String description;
}