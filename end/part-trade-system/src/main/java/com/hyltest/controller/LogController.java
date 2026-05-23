package com.hyltest.controller;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.IOperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/logs")
@SecurityRequirement(name = "Authorization")
@Tag(name = "操作日志接口",description = "用于获取管理员敏感操作日志的接口")
public class LogController {
    private final IOperateLogService operateLogService;

    @Operation(summary = "获取操作日志列表")
    @GetMapping("/getLogs")
    public Result getLogs(Integer currentPage, Integer pageSize) {
        log.info("获取操作日志列表");
        PageResult pageResult = operateLogService.getLogs(currentPage, pageSize);
        log.info("操作日志列表：{}", pageResult);
        return Result.success(pageResult);
    }

}
