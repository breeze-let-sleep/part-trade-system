package com.hyltest.controller;


import com.hyltest.anno.AdminLog;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.Customer;
import com.hyltest.service.ICustomerService;
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
@RequestMapping("/customers")
@SecurityRequirement(name = "Authorization")
@Tag(name = "顾客接口",description = "用于获取和管理顾客信息")
public class CustomerController {

    private final ICustomerService customerService;

    /**
     * 单个顾客信息获取
     */
    @Operation(summary = "获取单个顾客信息",description = "根据id获取顾客信息")
    @GetMapping("/getInfo/{id}")
    public Result getCustomerInfo(@PathVariable Integer id) {
        log.info("获取顾客信息：id={}", id);
        Customer customer = customerService.getCustomerInfo(id);
        return Result.success(customer);
    }

    /**
     * 分页请求顾客列表
     */
    @Operation(summary = "分页请求顾客列表",description = "分页获取顾客列表")
    @GetMapping("/getCustomers")
    public Result getCustomerPage(Integer currentPage, Integer pageSize) {
        log.info("分页获取顾客列表：currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult pageResult = customerService.getCustomerPage(currentPage, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 新增顾客信息
     */
    @Operation(summary = "新增顾客信息",description = "新增顾客信息")
    @AdminLog
    @PostMapping("/addCustomer")
    public Result addCustomer(@RequestBody Customer customer) {
        log.info("新增顾客：{}", customer);
        customerService.addCustomer(customer);
        return Result.success();
    }

    /**
     * 修改顾客信息
     */
    @Operation(summary = "修改顾客信息",description = "修改顾客信息")
    @AdminLog
    @PutMapping("/updateCustomer")
    public Result updateCustomer(@RequestBody Customer customer) {
        log.info("修改顾客信息：{}", customer);
        int result = customerService.updateCustomer(customer);
        return result > 0 ? Result.success() : Result.error("修改失败");
    }

    /**
     * 删除顾客信息
     */
    @Operation(summary = "删除顾客信息",description = "删除顾客信息")
    @AdminLog
    @DeleteMapping("/deleteCustomer/{id}")
    public Result deleteCustomer(@PathVariable Integer id) {
        log.info("删除顾客：id={}", id);
        int result = customerService.deleteCustomer(id);
        return result > 0 ? Result.success() : Result.error("删除失败");
    }

    /**
     * 模糊查询顾客列表
     */
    @Operation(summary = "模糊查询顾客列表",description = "模糊查询顾客列表")
    @GetMapping("/likeCustomers")
    public Result likeCustomer(
            @RequestParam(required = false) String likeName,
            @RequestParam(required = false) String likeAddress,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime
    ) {
        log.info("模糊查询顾客：likeName={}, likeAddress={}, startTime={}, endTime={}",
                 likeName, likeAddress, startTime, endTime);
        PageResult customers = customerService.likeCustomers(likeName, likeAddress, startTime, endTime);
        return Result.success(customers);
    }
}
