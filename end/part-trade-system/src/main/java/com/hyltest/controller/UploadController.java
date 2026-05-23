package com.hyltest.controller;


import com.hyltest.constant.MessageConstant;
import com.hyltest.exception.UnknownException;
import com.hyltest.pojo.Result;
import com.hyltest.utils.AliyunOSSOperator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@Tag(name = "图片上传接口")
public class UploadController {

    @Autowired
    private AliyunOSSOperator  aliyunOSSOperator;
    /**
     * 文件上传到OSS
     */
    @Operation(summary = "上传图片到阿里云",description = "上传图片到阿里云，返回文件路径")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        //将文件交给阿里云上传工具类进行处理->引入工具类实例
        String url= null;
        try {
            url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        } catch (Exception e) {
            throw new UnknownException(MessageConstant.UPLOAD_FAILED);
        }
        log.info("文件上传OSS成功，url：{}",url);
        return Result.success(url);
    }
}
