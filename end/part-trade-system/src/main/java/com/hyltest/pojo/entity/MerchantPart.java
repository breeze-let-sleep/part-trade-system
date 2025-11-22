package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 供应商零件管理（商品表）
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MerchantPart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Integer id;

    /**
     * 供应商id，以1开头，共6位
     */
    private Integer merchantId;

    /**
     * 零件id
     */
    private Integer partId;

    /**
     * 是否发布上线（0:否;1:是）
     */
    private Boolean isPublish;


}
