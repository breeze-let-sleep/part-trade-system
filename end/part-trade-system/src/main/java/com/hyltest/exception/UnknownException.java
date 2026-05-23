package com.hyltest.exception;

/**
 * 未知异常
 */
public class UnknownException extends RuntimeException {
    public UnknownException(String message) {
        super(message);
    }
}
