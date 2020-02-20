package com.lms.result;

public class Result {
    private int status;
    private String msg;
    private Object data;

    public Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
