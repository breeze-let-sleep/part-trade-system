package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VMerchantCollectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id，以1开头，共6位
     */
    private Integer merchantId;

    /**
     * 供应商名字
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 公司简介
     */
    private String description;

    /**
     * 顾客id，以2开头，共6位
     */
    private Integer customerId;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;


}
