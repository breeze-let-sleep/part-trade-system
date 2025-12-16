package com.hyltest.controller;

import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.Order;
import com.hyltest.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    /**
     * 购买零件（下单）
     */
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody Order order) {
        log.info("购买零件：{}", order);
        orderService.addOrder(order);
        return Result.success();
    }

    /**
     * 获取待支付订单信息
     */
    @GetMapping("/getPaidMessage")
    public Result getPaidMessage(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("获取待支付订单信息：当前页={}, 页大小={}", currentPage, pageSize);
        PageResult pageResult = orderService.getPaidMessagePage(currentPage, pageSize);
        log.info("待支付订单信息：{}", pageResult);
        // 手动构建Result，自定义提示语
        Result result = new Result();
        result.setCode(1);
        result.setMsg("获取待支付订单成功");
        result.setData(pageResult);
        return result;
    }

    /**
     * 支付订单
     */
    @PutMapping("/payOrder/{orderId}")
    public Result payOrder(@PathVariable Integer orderId) {
        log.info("支付订单 - 订单ID: {}", orderId);

        if (orderId <= 0) {
            return Result.error("支付失败：订单ID非法");
        }

        boolean success = orderService.payOrder(orderId);
        if (success) {
            // 用默认success方法（msg=success）
            return Result.success();
        } else {
            return Result.error("支付失败：订单不存在/已支付/状态异常");
        }
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/removeOrder/{orderId}")
    public Result removeOrder(@PathVariable(required = true) Integer orderId) {
        log.info("删除订单 - 订单ID: {}", orderId);

        if (orderId <= 0) {
            return Result.error("删除失败：订单ID非法");
        }

        try {
            orderService.deleteOrderById(orderId);
            // 手动构建Result，自定义提示语+返回订单ID
            Result result = new Result();
            result.setCode(1);
            result.setMsg("删除订单成功（订单ID：" + orderId + "）");
            result.setData(orderId);
            return result;
        } catch (Exception e) {
            log.error("删除订单失败：订单ID={}, 异常信息={}", orderId, e.getMessage());
            return Result.error("删除失败：订单不存在/状态不允许删除");
        }
    }

    /**
     * 模糊查看已完成的所有订单
     */
    @GetMapping("/getEndLikeOrders")
    public Result getEndLikeOrders(
            @RequestParam(required = false) String partName,
            @RequestParam(required = false) Integer orderId,
            @RequestParam(required = false) Integer star,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @RequestParam(required = true, defaultValue = "1") Integer currentPage,
            @RequestParam(required = true, defaultValue = "7") Integer pageSize) {

        log.info("模糊查看已完成的所有订单：零件名={}, 订单ID={}, 星级={}, 开始时间={}, 结束时间={}, 当前页={}, 页大小={}",
                 partName, orderId, star, startTime, endTime, currentPage, pageSize);

        if (currentPage <= 0 || pageSize <= 0) {
            return Result.error("查询失败：分页参数非法（当前页/页大小必须大于0）");
        }

        PageResult pageResult = orderService.getFinishedOrderPage(partName, orderId, star, startTime, endTime, currentPage, pageSize);
        return Result.success(pageResult);
    }
}