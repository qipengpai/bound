package com.qpp.qiniuserver.controller;

import com.qpp.qiniuserver.qny.UploadDemo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UploadController {


    @GetMapping(value = "/UPLOAD")
    public String userUplodUpdate() {
        String name = null;
        try {
            UploadDemo uploadDemo = new UploadDemo();
            name = uploadDemo.upload("D:\\777.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println(name);
        return name;
    }
    @Value("${qiniu.access_key}")
    private String access_key;
    @Value("${qiniu.secret_key}")
    private String secret_key;
    @Value("${qiniu.bucketname}")
    private String bucketname;
    @Value("${qiniu.httpurl}")
    private String httpurl;
    @Value("${qiniu.fops}")
    private String fops;
    @Value("${qiniu.pipeline}")
    private String pipeline;
    @GetMapping(value = "/UPLOAD2")
    public String userUplodUpdate2() {

        return this.access_key+"   "+secret_key+"   "+bucketname+"   "+httpurl+"   "+fops+"   "+pipeline;

    }
}
