package com.dn.dnutils;


import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpTest {


    @Test
    void getClinicNo() throws IOException {

        String url1 = "http://localhost:8080/api/demo";
        for (int i = 0; i < 100; i++) {
            GetCinicThread getCinicThread = new GetCinicThread(url1);
            Thread thread = new Thread(getCinicThread);
            thread.start();
            System.out.println("线程1已启动");

        }
    }

    @Test
    void getClinicNO2(){
        String url = "http://localhost:8081/api/demo";
        for (int i = 0; i < 100; i++) {
            GetCinicThread getCinicThread1 = new GetCinicThread(url);
            Thread thread = new Thread(getCinicThread1);
            thread.start();
            System.out.println("线程2已经启动");
            getCinicThread1.run();
        }
    }


}
