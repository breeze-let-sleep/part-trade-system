package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "订单表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @Schema(description = "订单id", example = "1")
    private Integer id;

    /**
     * 供应商id，以1开头，共6位
     */
    @Schema(description = "供应商id，以1开头，共6位", example = "100001")
    private Integer merchantId;

    /**
     * 顾客id，以2开头，共6位
     */
    @Schema(description = "顾客id，以2开头，共6位", example = "200001")
    private Integer customerId;

    /**
     * 零件id
     */
    @Schema(description = "零件id", example = "1")
    private Integer partId;

    /**
     * 交易数量，单位：个
     */
    @Schema(description = "交易数量，单位：个", example = "10")
    private Integer amount;

    /**
     * 交易金额，单位：元
     */
    @Schema(description = "交易金额，单位：元", example = "500")
    private Integer totalPrice;

    /**
     * 是否成功（0：不成功；1：已签名，成功）
     */
    @Schema(description = "是否成功（0：不成功；1：已签名，成功）", example = "0")
    private Integer isSuccess;

    /**
     * 顾客是否付款（0：未付；1：已付）
     */
    @Schema(description = "顾客是否付款（0：未付；1：已付）", example = "0")
    private Integer isPaid;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;


}
