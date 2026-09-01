package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单合同表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@Schema(description = "订单合同表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    @Schema(description = "合同id", example = "1")
    private Integer id;

    /**
     * 订单id
     */
    @Schema(description = "订单id", example = "1")
    private Integer orderId;

    /**
     * 供应商签名
     */
    @Schema(description = "供应商签名", example = "供应商小明")
    private String merchantSignature;

    /**
     * 顾客签名
     */
    @Schema(description = "顾客签名", example = "顾客小明")
    private String customerSignature;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;


}
