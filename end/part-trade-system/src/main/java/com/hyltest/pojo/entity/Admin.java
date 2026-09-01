package com.hyltest.pojo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "管理员表")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id，以0开头，共6位
     */
    @Schema(description = "id，以0开头，共6位", example = "000001")
    private Integer id;

    /**
     * 管理员名字
     */
    @Schema(description = "管理员名字", example = "管理员小明")
    private String name;

    /**
     * 密码（MD5加密后密文）
     */
    @Schema(description = "密码（MD5加密后密文）", example = "1234")
    private String password;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码", example = "13800138000")
    private String phone;

    /**
     * 头像的地址
     */
    @Schema(description = "头像的地址", example = "https://part-trade.oss-cn-hangzhou.aliyuncs.com/avatar.jpg")
    private String avatar;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "创建时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    @Schema(description = "修改时间(YYYY-MM-DD HH:MM:SS)", example = "2025-11-22 15:30:00")
    private LocalDateTime updateTime;


}
