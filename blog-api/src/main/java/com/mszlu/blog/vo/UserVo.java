package com.mszlu.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author sfChampion
 */
@Data
public class UserVo {

    private String nickname;

    private String avatar;

    /**
     * 防止前端出现精度损失，把id转为string、
     * 分布式id比较长，传到前端，会有精度损失，必须转为string类型，进行传输
     */
    //@JsonSerialize(using = ToStringSerializer.class)
    //private Long id;
    private String id;
}