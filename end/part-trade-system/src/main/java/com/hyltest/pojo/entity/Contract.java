package com.hyltest.pojo.entity;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 供应商签名
     */
    private String merchantSignature;

    /**
     * 顾客签名
     */
    private String customerSignature;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;


}
