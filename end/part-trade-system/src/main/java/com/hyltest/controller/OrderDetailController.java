package com.hyltest.controller;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.OrderDetail;
import com.hyltest.service.IOrderDetailService;
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
@RequestMapping("/details")
@SecurityRequirement(name = "Authorization")
@Tag(name = "订单详情接口")
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @Operation(summary = "分页获取当前用户处理中的订单",description = "分页获取当前用户处理中的订单列表")
    @GetMapping("/getProcessingOrders")
    public Result getProcessingOrders(
            @Parameter(description = "当前页", example = "1") Integer currentPage,
            @Parameter(description = "每页大小", example = "7") Integer pageSize) {
        log.info("分页获取当前用户处理中的订单：currentPage: {}, pageSize: {}", currentPage, pageSize);
        PageResult pageResult = orderDetailService.getProcessingOrderPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    @Operation(summary = "分页获取当前用户已经结束的订单",description = "分页获取当前用户已经结束的订单列表")
    @GetMapping("/getEndOrders")
    public Result getEndOrders(
            @Parameter(description = "当前页", example = "1") Integer currentPage,
            @Parameter(description = "每页大小", example = "7") Integer pageSize) {
        log.info("分页获取当前用户已经结束的订单：currentPage: {}, pageSize: {}", currentPage, pageSize);
        PageResult pageResult = orderDetailService.getEndOrderPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    @Operation(summary = "收货并评价订单",description = "确认收货并对订单进行星级评价")
    @PutMapping("/evaluateOrder")
    public Result evaluateOrder(@RequestBody OrderDetail orderDetail) {
        log.info("收货并评价订单：orderId: {}, star: {}, evaluate: {}", orderDetail.getOrderId(), orderDetail.getStar(),orderDetail.getEvaluate());
        orderDetailService.evaluateOrder(orderDetail);
        return Result.success();
    }

    @Operation(summary = "发货",description = "根据订单详情id对订单进行发货")
    @PutMapping("/deliver/{id}")
    public Result deliver(
            @Parameter(description = "订单详情id", example = "1")
            @PathVariable Integer id
    ) {
        log.info("发货：订单详情id: {}", id);
        orderDetailService.deliverById(id);
        return Result.success();
    }
}
