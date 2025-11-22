package com.hyltest.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;


/**
 * JWT 工具类
 * 令牌：第一部分：令牌类型与签名算法、第二部分：自定义数据（负载）、第三部分：将一二部分融合
 * 加入密钥，通过签名算法计算而来（防止篡改）
 */
public class JWTUtils {

    // 密钥（需和生成时一致）
    private static final String SECRET_KEY = "5LiA6L2u5byv5byv55qE5piO5pyI";

    // 有效期：24小时（毫秒）
    private static final long EXPIRATION = 24 * 60 * 60 * 1000;

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义数据（负载）
     * @return JWT 字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)  //添加自定义数据：令牌的第二部分
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))   //设置有效期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) //设置签名算法和密钥：令牌的第一部分
                .compact(); //生成令牌
    }

    /**
     * 传入完整的令牌，解析 JWT 令牌
     *
     * @param token JWT 字符串
     * @return 解析后的 Claims 数据
     */
    public static Map<String, Object> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)  //设置密钥，需要与生成时一致
                .parseClaimsJws(token)  //解析令牌
                .getBody(); //获取负载数据
    }
}
