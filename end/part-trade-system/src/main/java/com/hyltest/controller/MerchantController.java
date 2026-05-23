package com.hyltest.controller;

import com.hyltest.anno.AdminLog;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.Merchant;
import com.hyltest.service.IMerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/merchants")
@SecurityRequirement(name = "Authorization")
@Tag(name = "供应商接口",description = "供应商接口")
public class MerchantController {

    private final IMerchantService merchantService;

    @Operation(summary = "获取单个供应商信息")
    @GetMapping("/getInfo/{id}")
    public Result getMerchantById(@PathVariable Integer id) {
        log.info("获取单个供应商信息：id={}", id);
        Merchant merchant = merchantService.getMerchantById(id);
        return Result.success(merchant);
    }

    /**
     * 分页接口：返回完整分页数据（总记录数+当前页数据）
     * 调用getMerchantPage方法，用上PageResult
     */
    @Operation(summary = "分页获取供应商列表")
    @GetMapping("/getMerchants")
    public Result getMerchantList(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "7") int pageSize) {
        log.info("分页获取供应商列表（完整数据）：currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult pageResult = merchantService.getMerchantPage(currentPage, pageSize); // 调用新增方法
        return Result.success(pageResult); // 返回PageResult封装的完整数据
    }

    @Operation(summary = "模糊查询供应商列表")
    @GetMapping("/likeMerchants")
    public Result searchMerchants(
            @RequestParam(required = false) String likeName,
            @RequestParam(required = false) String likeAddress,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        log.info("模糊查询供应商列表：likeName={}, likeAddress={}, startTime={}, endTime={}", likeName, likeAddress, startTime, endTime);
        PageResult merchants = merchantService.searchMerchants(likeName, likeAddress, startTime, endTime);
        return Result.success(merchants);
    }

    @Operation(summary = "新增供应商信息")
    @AdminLog
    @PostMapping("/addMerchant")
    public Result addMerchant(@RequestBody Merchant merchant) {
        log.info("新增供应商信息：merchant={}", merchant.getName());
        merchantService.addMerchant(merchant);
        return Result.success();
    }

    @Operation(summary = "修改供应商信息")
    @AdminLog
    @PutMapping("/updateMerchant")
    public Result updateMerchant(@RequestBody Merchant merchant) {
        log.info("修改供应商信息： merchantName={}", merchant.getName());
        merchantService.updateMerchant(merchant);
        return Result.success();
    }

    @Operation(summary = "删除供应商信息")
    @AdminLog
    @DeleteMapping("/deleteMerchant/{id}")
    public Result deleteMerchant(@PathVariable Integer id) {
        log.info("删除供应商信息：id={}", id);
        merchantService.deleteMerchant(id);
        return Result.success();
    }
}