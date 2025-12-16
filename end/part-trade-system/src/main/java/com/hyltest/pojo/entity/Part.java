package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 零件表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Part implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 供应商id
     */
    private Integer merchantId;

    /**
     * 名称
     */
    private String name;

    /**
     * 颜色（1:红;2:黄;3:绿;4:蓝;5:白;6:黑;）
     */
    private Integer color;

    /**
     * 重量，单位kg
     */
    private Double weight;

    /**
     * 描述
     */
    private String description;

    /**
     * 库存，单位个
     */
    private Integer inventory;

    /**
     * 单价，单位元
     */
    private Integer singlePrice;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime updateTime;


}
