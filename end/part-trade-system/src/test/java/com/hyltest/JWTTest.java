package com.hyltest;

import com.hyltest.utils.JWTUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JWTTest {

    @Test
    public void testJWT() {
        //顾客小明
        Map<String, Object> claims = Map.of("id", 200002, "name", "顾客小明", "role", 2);
        String token = JWTUtils.generateToken(claims);
        System.out.println(token);
    }
}
