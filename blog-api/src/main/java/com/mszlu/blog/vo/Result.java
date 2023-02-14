package com.mszlu.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sfChampion
 * @date 2023/1/31 11:43
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(true,200,"success",data);
    }

    public static Result fail(int code,String msg) {
        return new Result(false,code,msg,null);
    }
}
