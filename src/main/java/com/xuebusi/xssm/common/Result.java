package com.xuebusi.xssm.common;

/**
 * 返回结果体
 * @author 学布斯
 * @version 2018-1-14
 */
public class Result<T> {

    private int code;
    private String message;
    private boolean success;
    private T data;
    private String requestId;
    private String requestTime;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result<T> success() {
        this.code = ViewHint.SUCCESS.getCode();
        this.success = true;
        this.message = ViewHint.SUCCESS.getMessage();
        return this;
    }

    public Result<T> success(T data) {
        this.code = ViewHint.SUCCESS.getCode();
        this.success = true;
        this.message = ViewHint.SUCCESS.getMessage();
        this.data = data;
        return this;
    }

    public Result<T> success(ViewHint viewHint) {
        this.code = viewHint.getCode();
        this.message = viewHint.getMessage();
        this.success = true;
        return this;
    }

    public Result<T> success(ViewHint viewHint, T data) {
        this.code = viewHint.getCode();
        this.message = viewHint.getMessage();
        this.success = true;
        this.data = data;
        return this;
    }

    public Result<T> error() {
        this.code = ViewHint.ERROR.getCode();
        this.message = ViewHint.ERROR.getMessage();
        return this;
    }

    public Result<T> error(ViewHint viewHint) {
        this.code = viewHint.getCode();
        this.message = viewHint.getMessage();
        return this;
    }

    public Result<T> error(ViewHint viewHint, T data) {
        this.code = viewHint.getCode();
        this.message = viewHint.getMessage();
        this.data = data;
        return this;
    }

    public Result<T> error(T data) {
        this.code = ViewHint.ERROR.getCode();
        this.message = ViewHint.ERROR.getMessage();
        this.data = data;
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                ", requestId='" + requestId + '\'' +
                ", requestTime='" + requestTime + '\'' +
                '}';
    }
}
