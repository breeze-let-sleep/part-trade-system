package com.hyltest.aop;


import com.hyltest.mapper.AdminMapper;
import com.hyltest.mapper.OperateLogMapper;
import com.hyltest.pojo.entity.OperateLog;
import com.hyltest.utils.CurrentHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    private final AdminMapper adminMapper;
    private final OperateLogMapper operateLogMapper;

    @Around("@annotation(com.hyltest.anno.AdminLog)")
    public Object recordAdminOperateLog(ProceedingJoinPoint pjp) throws Throwable{
        OperateLog operateLog = new OperateLog();
        //-----通过pjp获取想要的数据------
        //获取执行的方法名
        String methodName = pjp.getSignature().getName();
        //获取目标类
        String className = pjp.getTarget().getClass().getSimpleName();
        //获取当前操作时间
        operateLog.setCreateTime(LocalDateTime.now());
        //获取当前操作人id
        Integer id = CurrentHolder.getCurrentId();
        //根据id获取当前操作人名字
        String name = adminMapper.getNameById(id);
        //组装操作日志
        operateLog.setAdminId(id);
        operateLog.setMethod(name+"操作了"+className+"."+methodName+"方法");

        Object result = null;
        try {
            // 执行原方法
            result = pjp.proceed();
            return result;
        } catch (Exception e) {
            // 继续抛出异常，不影响业务逻辑
            throw e;
        } finally {
            // 异步保存日志，避免影响主业务流程
            CompletableFuture.runAsync(() -> {
                try {
                    //保存操作日志
                    operateLogMapper.insertNewOperateLog(operateLog);
                } catch (Exception ex) {
                    // 记录日志保存失败，但不影响业务
                    log.error("保存操作日志失败: {}", ex.getMessage(), ex);
                }
            });
        }
    }

}
