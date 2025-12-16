package com.hyltest.controller;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.IContractService;
import com.hyltest.service.IVOrderCompleteInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final IVOrderCompleteInfoService orderCompleteInfoService;
    private final IContractService contractService;

    @GetMapping("/getOrders")
    public Result getOrders(Integer currentPage, Integer pageSize) {
        log.info("分页获取当前用户未签名订单：currentPage: {}, pageSize: {}", currentPage, pageSize);
        PageResult pageResult = orderCompleteInfoService.getProcessingContractPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/getOrderDetail/{orderId}")
    public Result getOrderDetail(@PathVariable Integer orderId) {
        log.info("查询订单详情：orderId: {}", orderId);
        return Result.success(orderCompleteInfoService.getProcessingOrderDetail(orderId));
    }

    @PutMapping("/signature")
    public Result signature(Integer orderId, String value) {
        log.info("签名订单：orderId: {}, value: {}", orderId, value);
        String msg = contractService.signature(orderId, value);
        if (msg == null){
            return Result.success();
        }
        return Result.success(msg);
    }
}
