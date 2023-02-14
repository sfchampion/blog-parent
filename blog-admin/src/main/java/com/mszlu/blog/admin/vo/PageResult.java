package com.mszlu.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author sfChampion
 * @date 2023/2/10 14:47
 */

@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
