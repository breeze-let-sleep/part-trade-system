package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流程id
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 是否发货（0：未发货；1：已发货-运输中）
     */
    private Integer isDeliver;

    /**
     * 是否收到货物（0：未收到；1：已收到）
     */
    private Integer isTake;

    /**
     * 星级评价（1~5星）
     */
    private Integer star;

    /**
     * 评价内容
     */
    private String evaluate;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime updateTime;


}
