package com.hyltest.controller;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.OrderDetail;
import com.hyltest.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/details")
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @GetMapping("/getProcessingOrders")
    public Result getProcessingOrders(Integer currentPage, Integer pageSize) {
        log.info("分页获取当前用户处理中的订单：currentPage: {}, pageSize: {}", currentPage, pageSize);
        PageResult pageResult = orderDetailService.getProcessingOrderPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    @GetMapping("/getEndOrders")
    public Result getEndOrders(Integer currentPage, Integer pageSize) {
        log.info("分页获取当前用户已经结束的订单：currentPage: {}, pageSize: {}", currentPage, pageSize);
        PageResult pageResult = orderDetailService.getEndOrderPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    @PutMapping("/evaluateOrder")
    public Result evaluateOrder(@RequestBody OrderDetail orderDetail) {
        log.info("收货并评价订单：orderId: {}, star: {}, evaluate: {}", orderDetail.getOrderId(), orderDetail.getStar(),orderDetail.getEvaluate());
        orderDetailService.evaluateOrder(orderDetail);
        return Result.success();
    }

    @PutMapping("/deliver/{id}")
    public Result deliver(@PathVariable Integer id) {
        log.info("发货：订单详情id: {}", id);
        orderDetailService.deliverById(id);
        return Result.success();
    }
}
