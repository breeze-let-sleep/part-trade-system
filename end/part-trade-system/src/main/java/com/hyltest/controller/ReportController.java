package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Authorization")
@Tag(name = "报表接口")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "获取报表数据")
    @GetMapping("/report")
    public Result getReport(){
        return Result.success(reportService.getReport());
    }
}
