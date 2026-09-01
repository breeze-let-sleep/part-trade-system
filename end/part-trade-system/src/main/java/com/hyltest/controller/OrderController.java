package com.hyltest.controller;

import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.Order;
import com.hyltest.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/orders")
@SecurityRequirement(name = "Authorization")
@Tag(name = "订单管理")
public class OrderController {

    private final IOrderService orderService;

    /**
     * 购买零件（下单）
     */
    @Operation(summary = "购买零件",description = "根据订单信息下单购买零件")
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody Order order) {
        log.info("购买零件：{}", order);
        orderService.addOrder(order);
        return Result.success();
    }

    /**
     * 获取待支付订单信息
     */
    @Operation(summary = "获取待支付订单信息",description = "分页获取当前用户待支付的订单信息")
    @GetMapping("/getPaidMessage")
    public Result getPaidMessage(
            @Parameter(description = "当前页，默认为1", example = "1")
            @RequestParam(defaultValue = "1") Integer currentPage,
            @Parameter(description = "每页大小，默认为10", example = "10")
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
     * todo 添加顾客、供应商余额字段，实现扣减（如果可以再实现抽成--->只抽到root中，普通admin工资由root支付）
     */
    @Operation(summary = "支付订单",description = "根据订单id支付订单")
    @PutMapping("/payOrder/{orderId}")
    public Result payOrder(
            @Parameter(description = "订单id", example = "1")
            @PathVariable Integer orderId
    ) {
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
    @Operation(summary = "删除订单",description = "根据订单id删除订单")
    @DeleteMapping("/removeOrder/{orderId}")
    public Result removeOrder(
            @Parameter(description = "订单id", example = "1")
            @PathVariable(required = true) Integer orderId
    ) {
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
    @Operation(summary = "模糊查看已完成的所有订单",description = "根据零件名称、星级、时间范围等条件模糊查询已完成的订单列表，支持分页")
    @GetMapping("/getEndLikeOrders")
    public Result getEndLikeOrders(
            @Parameter(description = "零件名称", example = "六角螺丝")
            @RequestParam(required = false) String partName,
            @Parameter(description = "订单id", example = "1")
            @RequestParam(required = false) Integer orderId,
            @Parameter(description = "星级评价（1~5星）", example = "5")
            @RequestParam(required = false) Integer star,
            @Parameter(description = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-01 00:00:00")
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @Parameter(description = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-30 23:59:59")
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @Parameter(description = "当前页，默认为1", example = "1")
            @RequestParam(required = true, defaultValue = "1") Integer currentPage,
            @Parameter(description = "每页大小，默认为7", example = "7")
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