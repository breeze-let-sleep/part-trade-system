package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator  aliyunOSSOperator;
    /**
     * 文件上传到OSS
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        //将文件交给阿里云上传工具类进行处理->引入工具类实例
        String url=aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传OSS成功，url：{}",url);
        return Result.success(url);
    }
}
