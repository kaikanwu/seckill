package com.k.seckill.base.result;


/**
 * 静态方法可以帮助我们快速为javaBean赋值，得到我们想要的格式结果
 * 这个javaBean转成json将会是{"code":"500201","message":"用户名或密码错误","data":...}
 * @param <T>
 */
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    /**
     * fail
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure() {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }


    public static <T> Result<T> failure(T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);

        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }




    // success


    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }


    public static <T> Result<T> success(ResultCode resultCode) {
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        return result;
    }


    /**
     * set ResultCode
     * @param resultCode
     */
    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
