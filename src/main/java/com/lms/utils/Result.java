package com.lms.utils;

public class Result<T> {
    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public Result<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
