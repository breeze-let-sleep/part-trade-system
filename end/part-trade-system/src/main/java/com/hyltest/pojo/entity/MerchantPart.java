package com.hyltest.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "供应商零件管理（商品表）")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MerchantPart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @Schema(description = "商品id", example = "1")
    private Integer id;

    /**
     * 供应商id，以1开头，共6位
     */
    @Schema(description = "供应商id，以1开头，共6位", example = "100001")
    private Integer merchantId;

    /**
     * 零件id
     */
    @Schema(description = "零件id", example = "1")
    private Integer partId;

    /**
     * 是否发布上线（0:否;1:是）
     */
    @Schema(description = "是否发布上线（0:否;1:是）", example = "true")
    private Boolean isPublish;


}
