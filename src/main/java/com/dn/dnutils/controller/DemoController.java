package com.dn.dnutils.controller;

import com.dn.dnutils.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private LockService lockService;

    @GetMapping
    public void lock(String name) throws InterruptedException {
        if (lockService.getLock()) {
            System.out.println("获取所成功");
            System.out.println("当前线程："+Thread.currentThread().getName());
            Thread.sleep(new Random().nextInt(3000));
            lockService.removeLock();
            System.out.println("解锁成功！");
        }
    }

}
