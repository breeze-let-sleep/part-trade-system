package com.hyltest.controller;


import com.hyltest.anno.AdminLog;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.Admin;
import com.hyltest.service.IAdminService;
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
@RequestMapping("/admins")
@SecurityRequirement(name = "Authorization")
@Tag(name = "管理员接口", description = "用于获取和管理普通管理员的接口，属于系统管理员才有的权限")
public class AdminController {

    private final IAdminService adminService;

    @Operation(summary = "获取单个管理员信息", description = "根据id获取管理员详细信息")
    @GetMapping("/getInfo/{id}")
    public Result getAdminById(
            @Parameter(description = "管理员的id", example = "000001")
            @PathVariable Integer id
    ) {
        log.info("获取单个管理员信息：id={}", id);
        Admin admin = adminService.getAdminById(id);
        return Result.success(admin);
    }

    @Operation(summary = "分页获取管理员列表", description = "分页获取管理员详细信息列表")
    @GetMapping("/getAdmins")
    public Result getAdminList(
            @Parameter(description = "当前页，默认为1", example = "1")
            @RequestParam(defaultValue = "1") Integer currentPage,
            @Parameter(description = "每页大小，默认为7", example = "7")
            @RequestParam(defaultValue = "7") Integer pageSize) {
        log.info("分页获取管理员列表（完整数据）：currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult pageResult = adminService.getAdminPage(currentPage, pageSize); // 调用新增方法
        return Result.success(pageResult); // 返回PageResult封装的完整数据
    }

    @Operation(summary = "模糊查询管理员", description = "模糊查询管理员列表")
    @GetMapping("/likeAdmins")
    public Result searchAdmins(
            @Parameter(description = "模糊查询的输入", example = "小明")
            @RequestParam(required = false) String input,
            @Parameter(description = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-01 00:00:00")
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @Parameter(description = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-30 23:59:59")
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        log.info("模糊查询管理员列表：input={}, startTime={}, endTime={}", input, startTime, endTime);
        PageResult admins = adminService.searchAdminsByName(input, startTime, endTime);
        return Result.success(admins);
    }

    @Operation(summary = "新增管理员信息", description = "新增管理员信息，新增成功后返回成功提示")
    @AdminLog
    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody Admin admin) {
        log.info("新增管理员信息：admin={}", admin);
        adminService.addAdmin(admin);
        return Result.success();
    }

    @Operation(summary = "修改管理员信息", description = "修改管理员信息，并返回修改后的管理员信息")
    @AdminLog
    @PutMapping("/updateAdmin")
    public Result updateAdmin(@RequestBody Admin admin) {
        log.info("修改管理员信息 admin={}", admin);
        Admin updatedAdmin = adminService.updateAdmin(admin);
        return Result.success(updatedAdmin);
    }

    @Operation(summary = "删除管理员信息", description = "根据id删除管理员信息")
    @AdminLog
    @DeleteMapping("/deleteAdmin/{id}")
    public Result deleteAdmin(
            @Parameter(description = "管理员的id", example = "000001")
            @PathVariable Integer id
    ) {
        log.info("删除管理员信息：id={}", id);
        boolean success = adminService.deleteAdmin(id);
        return success ? Result.success() : Result.error("管理员删除失败");
    }
}
