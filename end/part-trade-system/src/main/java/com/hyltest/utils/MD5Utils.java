package com.hyltest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * 对字符串进行MD5加密（返回32位小写十六进制字符串）
     */
    public static String encrypt(String input) {
        try {
            // 1. 获取MD5消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2. 将字符串转换为字节数组并计算哈希值
            byte[] hashBytes = md.digest(input.getBytes());

            // 3. 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // 补零确保两位
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不可用", e);
        }
    }
}