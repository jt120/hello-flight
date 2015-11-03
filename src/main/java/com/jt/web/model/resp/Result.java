package com.jt.web.model.resp;

/**
 * since 2015/10/29.
 */
public class Result<T> {

    private int status;

    private String msg;

    private T t;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
