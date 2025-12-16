package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.pojo.VO.UserVO;
import com.hyltest.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody UserVO userVO){
        log.info("用户登录：{}",userVO);
        String msg = loginService.login(userVO);
        if (msg.contains("非法")){
            return Result.error(msg);
        }
        return Result.success(msg);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserVO userVO){
        log.info("用户注册：{}",userVO);
        loginService.register(userVO);
        return Result.success();
    }
}
