package com.dn.dnutils.controller;

import com.dn.dnutils.service.AppointService;
import com.dn.dnutils.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private LockService lockService;

    @Autowired
    private AppointService appointService ;

    @Autowired
    private RedisTemplate redisTemplate ;


    @GetMapping
    public boolean lock(String name) throws InterruptedException, IOException {
//        int i = appointService
//                .lockClinic();
        ValueOperations valueOperations = redisTemplate.opsForValue();
//        System.out.println("Qingqiu ");
        if (lockService.getLock()) {
            int i = appointService
                    .lockClinic();
            if(i>0){
                System.out.println("锁号成功");
            }else{
                System.out.println("锁号失败");
            }
            if(lockService.removeLock()){
                System.out.println("解锁成功！");
            }
        }
        return true ;
    }

}
