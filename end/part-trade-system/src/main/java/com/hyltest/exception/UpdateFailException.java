package com.hyltest.exception;

/**
 * 修改失败异常
 */
public class UpdateFailException extends RuntimeException {
    public UpdateFailException(String message) {
        super(message);
    }
}
