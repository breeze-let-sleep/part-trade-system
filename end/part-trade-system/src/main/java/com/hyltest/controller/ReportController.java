package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public Result getReport(){
        return Result.success(reportService.getReport());
    }
}
