package com.hyltest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "分页获取零件列表",description = "分页获取零件列表，返回零件信息列表")
    @GetMapping("/getParts")
    public Result getPartPage(
            @Parameter(description = "当前页", example = "1") Integer currentPage,
            @Parameter(description = "每页大小", example = "7") Integer pageSize) {
        log.info("分页获取零件列表： currentPage={}, pageSize={}", currentPage, pageSize);
        PageResult pageResult = partService.getPartPage(currentPage, pageSize);
        log.info("分页获取零件列表结果：{}", pageResult);
        return Result.success(pageResult);
    }

    /**
     * 模糊查询零件
     */
    @Operation(summary = "模糊查询零件",description = "根据零件id、名称、重量、颜色、时间范围等条件模糊查询零件列表")
    @GetMapping("/likeParts")
    public Result likePart(
            @Parameter(description = "零件id", example = "1")
            @RequestParam(required = false) Integer partId,
            @Parameter(description = "零件名称", example = "螺丝")
            @RequestParam(required = false) String partName,
            @Parameter(description = "重量，单位kg", example = "5.5")
            @RequestParam(required = false) Double weight,
            @Parameter(description = "颜色（1:红;2:黄;3:绿;4:蓝;5:白;6:黑;）", example = "1")
            @RequestParam(required = false) Integer color,
            @Parameter(description = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-01 00:00:00")
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @Parameter(description = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-11-30 23:59:59")
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
            @Parameter(description = "是否发布上线（0:否;1:是）", example = "1")
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
    @Operation(summary = "新增零件信息",description = "新增零件信息，新增成功后返回成功提示")
    @PostMapping("/addPart")
    public Result addPart(@RequestBody Part part) {
        log.info("新增零件：{}", part);
        partService.addPart(part);
        return Result.success();
    }

    /**
     * 修改零件信息
     */
    @Operation(summary = "修改零件信息",description = "修改零件信息，修改成功后返回成功提示")
    @PutMapping("/updatePart")
    public Result updatePart(@RequestBody Part part) {
        log.info("修改零件信息：{}", part);
        partService.updatePart(part);
        return Result.success();
    }

    /**
     * 修改零件上线信息
     */
    @Operation(summary = "修改零件上线信息",description = "根据商品id修改零件的发布上线状态")
    @PutMapping("/publishPart/{id}/{isPublish}")
    public Result publishPart(
            @Parameter(description = "商品id", example = "1")
            @PathVariable Integer id,
            @Parameter(description = "是否发布上线（0:否;1:是）", example = "1")
            @PathVariable Integer isPublish) {
        log.info("修改零件发布状态：id={}, isPublish={}", id, isPublish);
        partService.publishPart(id, isPublish);
        return Result.success();
    }

    /**
     * 删除零件信息
     */
    @Operation(summary = "删除零件信息",description = "根据零件id删除零件信息")
    @DeleteMapping("/deletePart/{id}")
    public Result deletePart(
            @Parameter(description = "零件id", example = "1")
            @PathVariable Integer id
    ) {
        log.info("删除零件：id={}", id);
        partService.deletePart(id);
        return Result.success();
    }
}
