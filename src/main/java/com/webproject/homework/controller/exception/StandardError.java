package com.webproject.homework.controller.exception;

public class StandardError {

    private Integer status;
    private String msg;

    public StandardError(Integer status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
