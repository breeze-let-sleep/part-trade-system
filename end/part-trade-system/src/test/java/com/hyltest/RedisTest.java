package com.hyltest;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.*;

import java.time.Duration;

public class RedisTest {

    @Test
    public void testRedisClass() {
        RedisTemplate redisTemplate = new RedisTemplate();
        HashOperations hashOperations = redisTemplate.opsForHash();

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // 设置缓存: key, value, 过期时间(分钟)
        operations.set("name", "hyl", Duration.ofMinutes(5));
    }
}
