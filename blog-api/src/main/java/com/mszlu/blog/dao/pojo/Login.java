package com.mszlu.blog.dao.pojo;

import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/2/17 15:52
 */

@Data
public class Login {

    private String openid;

    private String mobilePhoneNumber;

    private String code;

    private String ip;
}
