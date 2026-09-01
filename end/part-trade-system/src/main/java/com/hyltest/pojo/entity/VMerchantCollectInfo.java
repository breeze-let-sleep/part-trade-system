package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 收藏管理员详情视图
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@Schema(description = "收藏管理员详情视图")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VMerchantCollectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id，以1开头，共6位
     */
    @Schema(description = "id，以1开头，共6位", example = "100001")
    private Integer merchantId;

    /**
     * 供应商名字
     */
    @Schema(description = "供应商名字", example = "供应商小明")
    private String name;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码", example = "13800138000")
    private String phone;

    /**
     * 地址
     */
    @Schema(description = "地址", example = "北京市海淀区中关村大街1号")
    private String address;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址", example = "https://part-trade.oss-cn-hangzhou.aliyuncs.com/avatar.jpg")
    private String avatar;

    /**
     * 公司简介
     */
    @Schema(description = "公司简介", example = "公司成立于2020年，专营各类零件销售")
    private String description;

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
