package com.hyltest.handler;

import com.hyltest.exception.BaseException;
import com.hyltest.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

 /**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice  //关键注解
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler  //关键注解
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

}