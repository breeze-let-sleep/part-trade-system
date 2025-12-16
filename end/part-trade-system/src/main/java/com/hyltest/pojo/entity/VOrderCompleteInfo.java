package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情视图
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VOrderCompleteInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 供应商id，以1开头，共6位
     */
    private Integer merchantId;

    /**
     * 顾客id，以2开头，共6位
     */
    private Integer customerId;

    /**
     * 零件id
     */
    private Integer partId;

    /**
     * 交易数量，单位：个
     */
    private Long amount;

    /**
     * 交易金额，单位：元
     */
    private Long totalPrice;

    /**
     * 是否成功（0：不成功；1：已签名，成功）
     */
    private Integer isSuccess;

    /**
     * 顾客是否付款（0：未付；1：已付）
     */
    private Integer isPaid;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;

    /**
     * 供应商名字
     */
    private String merchantName;

    /**
     * 零件名称
     */
    private String partName;

    /**
     * 颜色（1:红;2:黄;3:绿;4:蓝;5:白;6:黑;）
     */
    private Integer color;

    /**
     * 重量，单位kg
     */
    private Double weight;

    /**
     * 单价，单位元
     */
    private Long singlePrice;

    /**
     * 顾客名字
     */
    private String customerName;

    //------------补充属性

    private LocalDateTime updateTime;
    private Integer star;
    private String evaluate;


}
