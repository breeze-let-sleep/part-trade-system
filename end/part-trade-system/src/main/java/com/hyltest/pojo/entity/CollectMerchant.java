package com.hyltest.pojo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收藏供应商表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@Schema(description = "收藏供应商表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CollectMerchant implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;


}
