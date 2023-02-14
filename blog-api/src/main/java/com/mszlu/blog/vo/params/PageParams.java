package com.mszlu.blog.vo.params;

import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/1/31 11:41
 */

@Data
public class PageParams {
    // 页数
    private int page = 1;
    // 每页数量
    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

    public String getMonth() {
        if (this.month != null && this.month.length() == 1) {
            return "0" + this.month;
        }
        return this.month;
    }
}
