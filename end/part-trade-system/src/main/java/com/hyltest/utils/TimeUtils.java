package com.hyltest.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 生成当前时间的标准形式{yyyy-MM-dd HH:mm:ss}
 */
public class TimeUtils {
    public static String getCurrentTime() {
        //使用LocalDataTime获取当前时间并格式化
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
