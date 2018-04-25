package com.xuebusi.xssm.common.exception;

import com.xuebusi.xssm.common.ViewHint;

/**
 * 异常
 */
public class ServiceException extends BaseException {
    public String getMessage() {
        return super.getMessage();
    }

    public ServiceException() {
        super(ViewHint.ERROR.getCode(), ViewHint.ERROR.getMessage());
    }

    public ServiceException(int code) {
        super(code);
    }

    public ServiceException(int code, String message) {
        super(code, message);
    }

    public ServiceException(int code, String message, Object data) {
        super(code, message, data);
    }

    public ServiceException(int code, String message, Throwable data) {
        super(code, message, data);
    }
}
