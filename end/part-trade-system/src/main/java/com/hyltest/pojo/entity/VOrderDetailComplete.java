package com.hyltest.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author Huang
 * @since 2025-12-14
 */
@Data
@Schema(description = "订单详情完整视图")
public class VOrderDetailComplete implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @Schema(description = "订单id", example = "1")
    @TableField("order_id")
    private Integer orderId;

    /**
     * 是否发货（0：未发货；1：已发货-运输中）
     */
    @Schema(description = "是否发货（0：未发货；1：已发货-运输中）", example = "1")
    @TableField("is_deliver")
    private Integer isDeliver;

    /**
     * 是否收到货物（0：未收到；1：已收到）
     */
    @Schema(description = "是否收到货物（0：未收到；1：已收到）", example = "1")
    @TableField("is_take")
    private Integer isTake;

    /**
     * 星级评价（1~5星）
     */
    @Schema(description = "星级评价（1~5星）", example = "5")
    @TableField("star")
    private Integer star;

    /**
     * 评价内容
     */
    @Schema(description = "评价内容", example = "物美价廉，物流很快")
    @TableField("evaluate")
    private String evaluate;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "2025-11-22 15:30:00")
    private LocalDateTime updateTime;

    /**
     * 供应商id
     */
    @Schema(description = "供应商id", example = "100001")
    private Integer merchantId;

    /**
     * 供应商名字
     */
    @Schema(description = "供应商名字", example = "供应商小明")
    @TableField("merchant_name")
    private String merchantName;

    /**
     * 地址
     */
    @Schema(description = "地址", example = "北京市海淀区中关村大街1号")
    @TableField("merchant_address")
    private String merchantAddress;

    /**
     * 顾客id
     */
    @Schema(description = "顾客id", example = "200001")
    private Integer customerId;

    /**
     * 顾客名字
     */
    @Schema(description = "顾客名字", example = "顾客小明")
    @TableField("customer_name")
    private String customerName;

    /**
     * 地址
     */
    @Schema(description = "地址", example = "北京市海淀区中关村大街1号")
    @TableField("customer_address")
    private String customerAddress;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "六角螺丝")
    @TableField("part_name")
    private String partName;

    /**
     * 交易数量，单位：个
     */
    @Schema(description = "交易数量，单位：个", example = "10")
    @TableField("purchase_amount")
    private Integer purchaseAmount;

    /**
     * 单价，单位元
     */
    @Schema(description = "单价，单位元", example = "50")
    @TableField("unit_price")
    private Integer unitPrice;

    /**
     * 交易金额，单位：元
     */
    @Schema(description = "交易金额，单位：元", example = "500")
    @TableField("total_price")
    private Integer totalPrice;


}
