package com.hyltest.controller;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.IContractService;
import com.hyltest.service.IVOrderCompleteInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/contracts")
@SecurityRequirement(name = "Authorization")
@Tag(name = "合同接口",description = "用于获取和操作合同接口")
public class ContractController {

    private final IVOrderCompleteInfoService orderCompleteInfoService;
    private final IContractService contractService;

    @Operation(summary = "分页获取当前用户未签名订单",description = "分页获取当前用户未签名的订单列表")
    @GetMapping("/getOrders")
    public Result getOrders(
            @Parameter(description = "当前页", example = "1") Integer currentPage,
            @Parameter(description = "每页大小", example = "7") Integer pageSize) {
        log.info("分页获取当前用户未签名订单：currentPage: {}, pageSize: {}", currentPage, pageSize);
        PageResult pageResult = orderCompleteInfoService.getProcessingContractPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    @Operation(summary = "查询订单详情",description = "根据订单id查询订单详情")
    @GetMapping("/getOrderDetail/{orderId}")
    public Result getOrderDetail(
            @Parameter(description = "订单id", example = "1")
            @PathVariable Integer orderId
    ) {
        log.info("查询订单详情：orderId: {}", orderId);
        return Result.success(orderCompleteInfoService.getProcessingOrderDetail(orderId));
    }

    @Operation(summary = "签名订单",description = "根据订单id和签名值进行签名")
    @PutMapping("/signature")
    public Result signature(
            @Parameter(description = "订单id", example = "1") Integer orderId,
            @Parameter(description = "签名值", example = "供应商小明") String value) {
        log.info("签名订单：orderId: {}, value: {}", orderId, value);
        String msg = contractService.signature(orderId, value);
        if (msg == null){
            return Result.success();
        }
        return Result.success(msg);
    }
}
