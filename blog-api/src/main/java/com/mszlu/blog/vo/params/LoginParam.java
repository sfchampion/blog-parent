package com.mszlu.blog.vo.params;

import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/2/8 14:30
 */

@Data
public class LoginParam {
    private String account;
    private String password;
    private String nickname;
}
