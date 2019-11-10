package com.dn.dnutils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DnUtilsApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate ;
    @Test
    void contextLoads() {
    }



    @Test
    public void setKeyValue(){
        String zhao1 = redisTemplate.opsForValue().get("zhao1");
        System.out.println(zhao1);

    }
}
