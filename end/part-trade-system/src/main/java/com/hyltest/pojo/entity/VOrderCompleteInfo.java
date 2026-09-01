package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "订单详情视图")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VOrderCompleteInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @Schema(description = "订单id", example = "1")
    private Integer orderId;

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
    private Long amount;

    /**
     * 交易金额，单位：元
     */
    @Schema(description = "交易金额，单位：元", example = "500")
    private Long totalPrice;

    /**
     * 是否成功（0：不成功；1：已签名，成功）
     */
    @Schema(description = "是否成功（0：不成功；1：已签名，成功）", example = "1")
    private Integer isSuccess;

    /**
     * 顾客是否付款（0：未付；1：已付）
     */
    @Schema(description = "顾客是否付款（0：未付；1：已付）", example = "1")
    private Integer isPaid;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;

    /**
     * 供应商名字
     */
    @Schema(description = "供应商名字", example = "供应商小明")
    private String merchantName;

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
     * 单价，单位元
     */
    @Schema(description = "单价，单位元", example = "50")
    private Long singlePrice;

    /**
     * 顾客名字
     */
    @Schema(description = "顾客名字", example = "顾客小明")
    private String customerName;

    //------------补充属性

    @Schema(description = "修改时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-22 15:30:00")
    private LocalDateTime updateTime;
    @Schema(description = "星级评价（1~5星）", example = "5")
    private Integer star;
    @Schema(description = "评价内容", example = "物美价廉，物流很快")
    private String evaluate;


}
