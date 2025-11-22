package com.hyltest.pojo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id，以0开头，共6位
     */
    private Integer id;

    /**
     * 管理员名字
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
     * 头像的地址
     */
    private String avatar;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime updateTime;


}
