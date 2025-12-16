package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Integer id;

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
    private Integer amount;

    /**
     * 交易金额，单位：元
     */
    private Integer totalPrice;

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


}
