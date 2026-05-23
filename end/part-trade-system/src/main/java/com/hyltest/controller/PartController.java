package com.hyltest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.hyltest.pojo.entity.Part;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.Result;
import com.hyltest.service.IPartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/parts")
@SecurityRequirement(name = "Authorization")
@Tag(name = "零件接口")
public class PartController {

    private final IPartService partService;

    /**
     * 分页请求供应商的零件列表（支持无merchantId查询所有已发布零件）
     */
    @Operation(summary = "分页获取零件列表")
    @GetMapping("/getParts")
    public Result getPartPage(Integer currentPage, Integer pageSize) {
        log.info("分页获取零件列表： currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult pageResult = partService.getPartPage(currentPage, pageSize);
        log.info("分页获取零件列表结果：{}", pageResult);
        return Result.success(pageResult);
    }

    /**
     * 模糊查询零件
     */
    @Operation(summary = "模糊查询零件")
    @GetMapping("/likeParts")
    public Result likePart(
            @RequestParam(required = false) Integer partId,
            @RequestParam(required = false) String partName,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) Integer color,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @RequestParam(required = false) Integer isPublish
    ) {
        log.info("模糊查询零件：partId={}, partName={}, weight={}, color={}, startTime={}, endTime={}, isPublish={}",
                 partId, partName, weight, color, startTime, endTime, isPublish);
        PageResult pageResult = partService.likePart(partId, partName, weight, color, startTime, endTime,isPublish);
        log.info("模糊查询零件结果：{}", pageResult);
        return Result.success(pageResult);
    }

    /**
     * 新增零件信息
     */
    @Operation(summary = "新增零件信息")
    @PostMapping("/addPart")
    public Result addPart(@RequestBody Part part) {
        log.info("新增零件：{}", part);
        partService.addPart(part);
        return Result.success();
    }

    /**
     * 修改零件信息
     */
    @Operation(summary = "修改零件信息")
    @PutMapping("/updatePart")
    public Result updatePart(@RequestBody Part part) {
        log.info("修改零件信息：{}", part);
        partService.updatePart(part);
        return Result.success();
    }

    /**
     * 修改零件上线信息
     */
    @Operation(summary = "修改零件上线信息")
    @PutMapping("/publishPart/{id}/{isPublish}")
    public Result publishPart(@PathVariable Integer id, @PathVariable Integer isPublish) {
        log.info("修改零件发布状态：id={}, isPublish={}", id, isPublish);
        partService.publishPart(id, isPublish);
        return Result.success();
    }

    /**
     * 删除零件信息
     */
    @Operation(summary = "删除零件信息")
    @DeleteMapping("/deletePart/{id}")
    public Result deletePart(@PathVariable Integer id) {
        log.info("删除零件：id={}", id);
        partService.deletePart(id);
        return Result.success();
    }
}
