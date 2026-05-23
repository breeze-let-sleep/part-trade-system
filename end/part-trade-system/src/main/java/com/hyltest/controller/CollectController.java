package com.hyltest.controller;

import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.ICollectMerchantService;
import com.hyltest.service.IVMerchantCollectInfoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "添加供应商为收藏")
    @PostMapping("/addCollect/{customerId}/{merchantId}")
    public Result addCollect(@PathVariable("customerId") Integer customerId,
                             @PathVariable("merchantId") Integer merchantId) {
        log.info("添加供应商为收藏：customerId={}, merchantId={}", customerId, merchantId);
        collectMerchantService.addCollect(customerId, merchantId);
        return Result.success();
    }

    /**
     * 取消收藏
     * DELETE /collects/removeCollect/{customerId}/{merchantId}
     */
    @Operation(summary = "取消收藏")
    @DeleteMapping("/removeCollect/{customerId}/{merchantId}")
    public Result removeCollect(@PathVariable("customerId") Integer customerId,
                                @PathVariable("merchantId") Integer merchantId) {
        log.info("取消收藏：customerId={}, merchantId={}", customerId, merchantId);
        collectMerchantService.removeCollect(customerId, merchantId);
        return Result.success();
    }

    /**
     * 模糊查询已收藏的供应商
     * GET /collects/likeCollects?inputMerchant=xxx&customerId=xxx
     */
    @Operation(summary = "模糊查询已收藏的供应商")
    @GetMapping("/likeCollects")
    public Result likeCollects(@RequestParam(required = false) String inputMerchant,
                               @RequestParam(required = false) Integer customerId,
                               @RequestParam(required = false, defaultValue = "1") Integer currentPage,
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
    @Operation(summary = "分页请求收藏的供应商列表")
    @GetMapping("/getCollects")
    public Result getCollects(@RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {
        log.info("分页请求收藏的供应商列表：currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult data = vMerchantCollectInfoService.pageCollects(currentPage, pageSize);
        return Result.success(data);
    }
}
