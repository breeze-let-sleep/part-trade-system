package com.hyltest.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class VOrderDetailComplete implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;

    /**
     * 是否发货（0：未发货；1：已发货-运输中）
     */
    @TableField("is_deliver")
    private Integer isDeliver;

    /**
     * 是否收到货物（0：未收到；1：已收到）
     */
    @TableField("is_take")
    private Integer isTake;

    /**
     * 星级评价（1~5星）
     */
    @TableField("star")
    private Integer star;

    /**
     * 评价内容
     */
    @TableField("evaluate")
    private String evaluate;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 供应商id
     */
    private Integer merchantId;

    /**
     * 供应商名字
     */
    @TableField("merchant_name")
    private String merchantName;

    /**
     * 地址
     */
    @TableField("merchant_address")
    private String merchantAddress;

    /**
     * 顾客id
     */
    private Integer customerId;

    /**
     * 顾客名字
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 地址
     */
    @TableField("customer_address")
    private String customerAddress;

    /**
     * 名称
     */
    @TableField("part_name")
    private String partName;

    /**
     * 交易数量，单位：个
     */
    @TableField("purchase_amount")
    private Integer purchaseAmount;

    /**
     * 单价，单位元
     */
    @TableField("unit_price")
    private Integer unitPrice;

    /**
     * 交易金额，单位：元
     */
    @TableField("total_price")
    private Integer totalPrice;


}
