package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 供应商管理零件详情视图
 * 只有当MerchantPart里有该零件时，该零件才会显示
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@Schema(description = "供应商管理零件详情视图")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VMerchantPartDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id，以1开头，共6位
     */
    @Schema(description = "供应商id，以1开头，共6位", example = "100001")
    private Integer merchantId;

    /**
     * 供应商名字
     */
    @Schema(description = "供应商名字", example = "供应商小明")
    private String merchantName;

    /**
     * 零件id
     */
    @Schema(description = "零件id", example = "1")
    private Integer partId;

    /**
     * 零件名称
     */
    @Schema(description = "零件名称", example = "六角螺丝")
    private String partName;

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
    private Long singlePrice;

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

    /**
     * 商品id
     */
    @Schema(description = "商品id", example = "1")
    private Integer id;

    /**
     * 是否发布上线（0:否;1:是）
     */
    @Schema(description = "是否发布上线（0:否;1:是）", example = "true")
    private Boolean isPublish;


    /**
     * 将要购买数量（用于商品获取）
     */
    @Schema(description = "将要购买数量（用于商品获取）", example = "10")
    private Integer amount;


}
