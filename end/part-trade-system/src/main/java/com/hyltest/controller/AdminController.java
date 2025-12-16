package com.hyltest.controller;



import com.hyltest.anno.AdminLog;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.Admin;
import com.hyltest.service.IAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final IAdminService adminService;

    @GetMapping("/getInfo/{id}")
    public Result getAdminById(@PathVariable Integer id) {
        log.info("获取单个管理员信息：id={}", id);
        Admin admin = adminService.getAdminById(id);
        return Result.success(admin);
    }

    @GetMapping("/getAdmins")
    public Result getAdminList(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "7") Integer pageSize) {
        log.info("分页获取管理员列表（完整数据）：currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult pageResult = adminService.getAdminPage(currentPage, pageSize); // 调用新增方法
        return Result.success(pageResult); // 返回PageResult封装的完整数据
    }


    @GetMapping("/likeAdmins")
    public Result searchAdmins(
            @RequestParam(required = false) String input,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        log.info("模糊查询管理员列表：input={}, startTime={}, endTime={}", input, startTime, endTime);
        PageResult admins = adminService.searchAdminsByName(input, startTime, endTime);
        return Result.success(admins);
    }

    @AdminLog
    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody Admin admin) {
        log.info("新增管理员信息：admin={}", admin);
        adminService.addAdmin(admin);
        return Result.success();
    }

    @AdminLog
    @PutMapping("/updateAdmin")
    public Result updateAdmin(@RequestBody Admin admin) {
        log.info("修改管理员信息 admin={}", admin);
        Admin updatedAdmin = adminService.updateAdmin(admin);
        return Result.success(updatedAdmin);
    }

    @AdminLog
    @DeleteMapping("/deleteAdmin/{id}")
    public Result deleteAdmin(@PathVariable Integer id) {
        log.info("删除管理员信息：id={}", id);
        boolean success = adminService.deleteAdmin(id);
        return success ? Result.success() : Result.error("管理员删除失败");
    }
}
