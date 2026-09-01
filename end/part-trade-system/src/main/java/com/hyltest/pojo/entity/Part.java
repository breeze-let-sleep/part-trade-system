package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 零件表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@Schema(description = "零件表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(description = "id", example = "1")
    private Integer id;

    /**
     * 供应商id
     */
    @Schema(description = "供应商id", example = "100001")
    private Integer merchantId;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "六角螺丝")
    private String name;

    /**
     * 颜色（1:红;2:黄;3:绿;4:蓝;5:白;6:黑;）
     */
    @Schema(description = "颜色（1:红;2:黄;3:绿;4:蓝;5:白;6:黑;）", example = "1")
    private Integer color;

    /**
     * 重量，单位kg
     */
    @Schema(description = "重量，单位kg", example = "5.5")
    private Double weight;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "不锈钢材质，物美价廉")
    private String description;

    /**
     * 库存，单位个
     */
    @Schema(description = "库存，单位个", example = "100")
    private Integer inventory;

    /**
     * 单价，单位元
     */
    @Schema(description = "单价，单位元", example = "50")
    private Integer singlePrice;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "修改时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-22 15:30:00")
    private LocalDateTime updateTime;


}
