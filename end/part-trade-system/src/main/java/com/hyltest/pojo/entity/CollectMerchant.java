package com.hyltest.pojo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CollectMerchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id，以1开头，共6位
     */
    private Integer merchantId;

    /**
     * 顾客id，以2开头，共6位
     */
    private Integer customerId;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;


}
