package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员操作日志表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@Schema(description = "管理员操作日志表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作id
     */
    @Schema(description = "操作id", example = "1")
    private Integer id;

    /**
     * id，以0开头，共6位
     */
    @Schema(description = "id，以0开头，共6位", example = "000001")
    private Integer adminId;

    /**
     * 操作方法描述
     */
    @Schema(description = "操作方法描述", example = "删除管理员信息")
    private String method;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;


}
