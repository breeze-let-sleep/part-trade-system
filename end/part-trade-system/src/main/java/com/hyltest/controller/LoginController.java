package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.pojo.VO.UserVO;
import com.hyltest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public Result login(UserVO userVO){
        //todo: 登录
        return Result.success();
    }

    @GetMapping("/register")
    public Result register(UserVO userVO){
        //todo: 注册
        return Result.success();
    }
}
