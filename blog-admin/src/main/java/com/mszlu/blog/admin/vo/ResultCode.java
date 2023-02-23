package com.mszlu.blog.admin.vo;

/**
 * @author sfChampion
 * @date 2023/2/8 14:43
 */
public enum ResultCode {
    DELETE_FAIL(1001,"删除失败");

    private int code;
    private String msg;

    ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
