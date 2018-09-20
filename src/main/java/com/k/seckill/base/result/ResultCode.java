package com.k.seckill.base.result;


/**
 * 使用枚举类型定义静态错误信息，方便修改，查找，添加。
 *
 * 我们约定，200为成功，500为失败，
 * 因为失败的情况和业务较多，我们用500开头，如5002**表示用户相关业务出错等
 */
public enum  ResultCode {


    //全局成功
    SUCCESS(200, "成功"),

    //全局失败
    FAIL(500, "失败"),


    //秒课相关的错误信息

    SECKILL_LINE_UP(500100, "排队中"),
    SECKILL_NO_QUOTE(500101, "非常抱歉该课程名额已满，请联系管理员。"),
    SECKILL_BOUGHT(500102, "你已购买该课程，请去订单页面查看。"),
    SECKILL_PATH_ERROR(500104, "你的请求地址不正确，请联系管理员。"),
    SECKILL_IP_OUTMAX(500105, "你的请求过于频繁，请稍后再试。"),

    /**
     * 和用户有关的错误
     * USER_开头
     * 错误码 500200 -500299
     */
    USER_LOGIN_ERROR(500201, "登录失败，用户名或密码错误，请重新输入。"),
    USER_HAS_EXISTED(500202, "用户名已存在，请更换用户名后重试。"),
    USER_NOT_LOGIN(500203, "用户名未登录或登录已失效，请重新登录。");


    private Integer code;
    private  String message;

    ResultCode(Integer code, String message){


        this.code = code;
        this.message = message;
    }


    /**
     * setter and getter
     * @return
     */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
