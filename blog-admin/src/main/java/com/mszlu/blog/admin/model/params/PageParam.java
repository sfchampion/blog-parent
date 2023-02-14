package com.mszlu.blog.admin.model.params;

import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/2/10 14:38
 */

@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;

}
