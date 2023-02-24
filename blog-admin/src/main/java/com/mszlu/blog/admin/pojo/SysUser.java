package com.mszlu.blog.admin.pojo;

import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/1/31 11:31
 */

@Data
public class SysUser {

    //@TableId(type = IdType.ASSIGN_ID) // 默认id类型
    // 以后用户多了，要进行分表操作，id需要使用分布式id
    //@TableId(type = IdType.AUTO) 数据库自增
    private String id;

    private String account;

    private Integer admin;

    private String avatar;

    private String createDate;

    private Integer deleted;

    private String email;

    //private String lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
