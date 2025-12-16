package com.hyltest.pojo.VO;

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
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String name;
    private String password;
    private int role;
    private String createTime;
    private String updateTime;
}
