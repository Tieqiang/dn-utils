package com.dn.dnutils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class LockService {


    @Autowired
    private RedisTemplate redisTemplate;


    private String key = "LOCK";
    private long expired = 60;

    private String value = "";

    public boolean getLock() {
        long l = System.currentTimeMillis();
        Random random = new Random();
        System.out.println(System.currentTimeMillis());
        while (System.currentTimeMillis() - l < this.expired * 1000) {
            if (redisTemplate.opsForValue().setIfAbsent(this.key, Thread.currentThread().getName(), this.expired, TimeUnit.SECONDS)) {
                return true;
            }

            try {
                Thread.sleep(10, random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println("程序休眠被打断");
            }
        }
        return false;
    }

    private boolean isLock() {
        Long time = redisTemplate.getConnectionFactory().getConnection().time();
        System.out.println(time);
        return redisTemplate.hasKey(this.key);
    }

    public boolean removeLock() {
        if (this.isLock()) {
            return redisTemplate.delete(this.key);
        }
        return true;

    }

}
