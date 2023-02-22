package com.mszlu.blog.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author sfChampion
 * @date 2023/2/17 17:26
 */

@Data
public class MsmVo {
    private String mobilePhoneNumber;

    private String templateCode;

    private Map<String,Object> param;
}
