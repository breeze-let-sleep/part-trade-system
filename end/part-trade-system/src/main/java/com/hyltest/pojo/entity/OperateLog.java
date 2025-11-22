package com.hyltest.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员操作日志表
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作id
     */
    private Integer id;

    /**
     * id，以0开头，共6位
     */
    private Integer adminId;

    /**
     * 操作方法描述
     */
    private String method;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private LocalDateTime createTime;


}
