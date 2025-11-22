package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 供应商表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Merchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id，以1开头，共6位
     */
    private Integer id;

    /**
     * 供应商名字
     */
    private String name;

    /**
     * 密码（MD5加密后密文）
     */
    private String password;

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
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime updateTime;


}
