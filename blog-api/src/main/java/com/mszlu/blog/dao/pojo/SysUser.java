package com.mszlu.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}
