package com.hyltest.controller;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.IOperateLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/logs")
public class LogController {
    private final IOperateLogService operateLogService;

    @GetMapping("/getLogs")
    public Result getLogs(Integer currentPage, Integer pageSize) {
        log.info("获取操作日志列表");
        PageResult pageResult = operateLogService.getLogs(currentPage, pageSize);
        log.info("操作日志列表：{}", pageResult);
        return Result.success(pageResult);
    }

}
