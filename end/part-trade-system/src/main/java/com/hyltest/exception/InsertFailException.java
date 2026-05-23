package com.hyltest.exception;

/**
 * 插入失败异常
 */
public class InsertFailException extends RuntimeException {
    public InsertFailException(String message) {
        super(message);
    }
}
