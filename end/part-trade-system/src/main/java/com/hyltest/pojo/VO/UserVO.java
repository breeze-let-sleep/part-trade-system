package com.hyltest.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录或注册时的用户数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String name;
    private String password;
    private int role;
}
