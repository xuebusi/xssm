package com.xuebusi.xssm.common.exception;

/**
 *
 */
public class BaseException extends RuntimeException {
    private int code;
    private String message;
    private Object data;
    Throwable cause;

    public Throwable getCause() {
        return this.cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public BaseException(int code) {
        this.code = code;
    }

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseException(int code, String message, Throwable cause) {
        this.code = code;
        this.message = message;
        this.data = this.data;
        this.cause = cause;
    }

    public int getCode() {
        return this.code;
    }
}