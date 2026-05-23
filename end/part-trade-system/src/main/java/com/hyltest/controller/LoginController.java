package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.pojo.VO.UserVO;
import com.hyltest.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
@RestController
@Tag(name = "用户登录相关接口", description = "用户登录相关接口，包括登录、注册与获取验证码")
public class LoginController {

    private static final Long VALIDATE_CODE_PERIOD = 5L;

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserVO userVO){
        log.info("用户登录：{}",userVO);
        String msg = loginService.login(userVO);
        if (msg.contains("非法")){
            return Result.error(msg);
        }
        return Result.success(msg);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserVO userVO){
        log.info("用户注册：{}",userVO);
        //1、检查code是否存在且一致
        String code = (String) redisTemplate.opsForValue().get("code");
        //2、存在且一致则注册，否则返回错误信息
        if (code == null || !code.equals(userVO.getCode())){
            return Result.error("验证码错误");
        }
        //3、注册
        loginService.register(userVO);
        return Result.success();
    }

    @Operation(summary = "获取验证码",description = "获取验证码，有效期5分钟")
    @GetMapping("/code")
    public Result getCode(){
        log.info("获取验证码");
        //1、随机生成6位大小验证码
        SecureRandom secureRandom = new SecureRandom();
        int num = 100000 + secureRandom.nextInt(900000);
        String code = String.valueOf(num);
        //2、将其存入Redis中，设置有效期5分钟
        ValueOperations sredis = redisTemplate.opsForValue();
        sredis.set("code", code, Duration.ofMinutes(VALIDATE_CODE_PERIOD));
        return Result.success(code);
    }
}
