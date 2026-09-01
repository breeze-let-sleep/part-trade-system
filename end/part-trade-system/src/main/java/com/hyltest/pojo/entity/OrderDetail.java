package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "订单详情表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流程id
     */
    @Schema(description = "订单流程id", example = "1")
    private Integer id;

    /**
     * 订单id
     */
    @Schema(description = "订单id", example = "1")
    private Integer orderId;

    /**
     * 是否发货（0：未发货；1：已发货-运输中）
     */
    @Schema(description = "是否发货（0：未发货；1：已发货-运输中）", example = "0")
    private Integer isDeliver;

    /**
     * 是否收到货物（0：未收到；1：已收到）
     */
    @Schema(description = "是否收到货物（0：未收到；1：已收到）", example = "0")
    private Integer isTake;

    /**
     * 星级评价（1~5星）
     */
    @Schema(description = "星级评价（1~5星）", example = "5")
    private Integer star;

    /**
     * 评价内容
     */
    @Schema(description = "评价内容", example = "物美价廉，物流很快")
    private String evaluate;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "修改时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-22 15:30:00")
    private LocalDateTime updateTime;


}
