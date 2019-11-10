package com.dn.dnutils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Service
public class AppointService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public int lockClinic() throws InterruptedException, IOException {
        int clinicNum = Integer.parseInt(redisTemplate.opsForValue().get("clinic"));

        if (clinicNum > 0) {
            Thread.sleep(new Random().nextInt(10000));
            int now = Integer.parseInt(redisTemplate.opsForValue().get("clinic"));

            redisTemplate.opsForValue().set("clinic", String.valueOf(now - 1));
            File file = new File(Thread.currentThread().getName()) ;
            if(!file.exists()){
                file.createNewFile();
            }
            return 1;
        }
        return -1;
    }

}
