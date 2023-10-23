package com.my.pro.exception;

public class ValidationBusinessException extends RuntimeException {

    public ValidationBusinessException(String message) {
        super(message);
    }

    public ValidationBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
