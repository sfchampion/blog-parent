package com.mszlu.blog.utils;

import com.mszlu.blog.vo.ResultCode;
import lombok.Data;

/**
 * @author sfChampion
 * @date 2023/2/20 22:57
 */

/**
 * 自定义全局异常类
 */
@Data
public class BlogException extends RuntimeException{
    /**
     * 异常状态码
     */
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param msg
     * @param code
     */
    public BlogException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCode
     */
    public BlogException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    @Override
    public String toString() {
        return "BlogException{" +
                "code=" + code +
                ", msg=" + this.getMessage() +
                '}';
    }
}
