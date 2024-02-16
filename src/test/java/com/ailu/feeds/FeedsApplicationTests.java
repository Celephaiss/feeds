package com.ailu.feeds;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class FeedsApplicationTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {
        stringRedisTemplate.opsForValue().set("hello", "haha");
        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
        stringRedisTemplate.opsForHash().put("st1", "key", "value");
        Object o = stringRedisTemplate.opsForHash().get("st1", "key");
        System.out.println(o);
    }

}
