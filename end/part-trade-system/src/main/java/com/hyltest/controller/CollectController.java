package com.hyltest.controller;

import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.ICollectMerchantService;
import com.hyltest.service.IVMerchantCollectInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏相关接口
 */
@Slf4j
@RestController
@RequestMapping("/collects")
@SecurityRequirement(name = "Authorization")
@Tag(name = "收藏接口",description = "用于获取收藏供应商的接口")
public class CollectController {

    @Autowired
    private ICollectMerchantService collectMerchantService;
    @Autowired
    private IVMerchantCollectInfoService vMerchantCollectInfoService;

    /**
     * 添加供应商为收藏
     * POST /collects/addCollect/{customerId}/{merchantId}
     */
    @Operation(summary = "添加供应商为收藏",description = "根据顾客id和供应商id添加收藏记录")
    @PostMapping("/addCollect/{customerId}/{merchantId}")
    public Result addCollect(
            @Parameter(description = "顾客id，以2开头，共6位", example = "200001")
            @PathVariable("customerId") Integer customerId,
            @Parameter(description = "供应商id，以1开头，共6位", example = "100001")
            @PathVariable("merchantId") Integer merchantId) {
        log.info("添加供应商为收藏：customerId={}, merchantId={}", customerId, merchantId);
        collectMerchantService.addCollect(customerId, merchantId);
        return Result.success();
    }

    /**
     * 取消收藏
     * DELETE /collects/removeCollect/{customerId}/{merchantId}
     */
    @Operation(summary = "取消收藏",description = "根据顾客id和供应商id取消收藏记录")
    @DeleteMapping("/removeCollect/{customerId}/{merchantId}")
    public Result removeCollect(
            @Parameter(description = "顾客id，以2开头，共6位", example = "200001")
            @PathVariable("customerId") Integer customerId,
            @Parameter(description = "供应商id，以1开头，共6位", example = "100001")
            @PathVariable("merchantId") Integer merchantId) {
        log.info("取消收藏：customerId={}, merchantId={}", customerId, merchantId);
        collectMerchantService.removeCollect(customerId, merchantId);
        return Result.success();
    }

    /**
     * 模糊查询已收藏的供应商
     * GET /collects/likeCollects?inputMerchant=xxx&customerId=xxx
     */
    @Operation(summary = "模糊查询已收藏的供应商",description = "根据供应商名称模糊查询已收藏的供应商列表，支持分页")
    @GetMapping("/likeCollects")
    public Result likeCollects(
            @Parameter(description = "模糊匹配的供应商名称", example = "小明")
            @RequestParam(required = false) String inputMerchant,
            @Parameter(description = "顾客id", example = "200001")
            @RequestParam(required = false) Integer customerId,
            @Parameter(description = "当前页，默认为1", example = "1")
            @RequestParam(required = false, defaultValue = "1") Integer currentPage,
            @Parameter(description = "每页大小，默认为10", example = "10")
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ) {
        log.info("模糊查询已收藏的供应商：inputMerchant={}, customerId={}, currentPage={}, pageSize={}", inputMerchant, customerId, currentPage, pageSize);
        PageResult data = vMerchantCollectInfoService.likeCollects(inputMerchant, customerId, currentPage, pageSize);
        return Result.success(data);
    }

    /**
     * 分页请求收藏的供应商列表
     * GET /collects/getCollects?currentPage=xxx&pageSize=xxx
     */
    @Operation(summary = "分页请求收藏的供应商列表",description = "分页获取已收藏的供应商列表")
    @GetMapping("/getCollects")
    public Result getCollects(
            @Parameter(description = "当前页", example = "1")
            @RequestParam("currentPage") Integer currentPage,
            @Parameter(description = "每页大小", example = "10")
            @RequestParam("pageSize") Integer pageSize) {
        log.info("分页请求收藏的供应商列表：currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult data = vMerchantCollectInfoService.pageCollects(currentPage, pageSize);
        return Result.success(data);
    }
}
