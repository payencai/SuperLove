package com.love.novalue.bean;

/**
 * Created by ckerv on 2018/2/4.
 */

public class Result<T> {



    public int resultCode;
    public String message;
    public String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int code) {
        this.resultCode = code;
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
