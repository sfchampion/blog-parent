package com.mszlu.blog.vo;

/**
 * @author sfChampion
 * @date 2023/2/8 14:43
 */
public enum ResultCode {
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    TOKEN_ERROR(10003,"token不合法"),
    ACCOUNT_EXIST(10004,"账号已存在"),
    UPLOAD_FAIL(10005,"上传失败"),
    CODE_ERROR(10006,"验证码错误"),
    SENDING_SMS_FAIL(10007,"发送短信失败"),
    DISABLE_USER_LOGIN(10008,"禁止用户登录"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),;

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
