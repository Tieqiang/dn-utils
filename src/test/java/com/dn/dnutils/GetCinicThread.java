package com.dn.dnutils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class GetCinicThread implements Runnable {

    private String url  ;

    public GetCinicThread(String url){
        this.url = url ;
    }

    @Override
    public void run() {

        try {
            connect(this.url);
        } catch (IOException e) {
            System.out.printf("请求异常");
            e.printStackTrace();
        }
    }

    public void connect(String u) throws IOException {
        URL url = new URL(this.url);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("method", "get");
        urlConnection.connect();
        urlConnection.getContent();
    }

}
