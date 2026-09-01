package com.hyltest.pojo.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 登录或注册时的用户数据
 */

/*
对于 MySQL 中 datetime 类型的字段（存储格式为 yyyy-MM-dd HH:mm:ss），
对应到 Java 对象的属性中，既可以使用 LocalDateTime，也可以使用 String
 */
@Data
@Schema(description = "登录或注册时的用户数据")
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    @Schema(description = "用户名", example = "小明")
    private String name;
    @Schema(description = "电话号码", example = "13800138000")
    private String phone;
    @Schema(description = "密码", example = "1234")
    private String password;
    @Schema(description = "角色", example = "2")
    private int role;
    @Schema(description = "创建时间", example = "2025-11-21 12:00:00")
    private String createTime;
    @Schema(description = "修改时间", example = "2025-11-22 15:30:00")
    private String updateTime;

    // 验证码
    @Schema(description = "验证码", example = "123456")
    private String code;
}
