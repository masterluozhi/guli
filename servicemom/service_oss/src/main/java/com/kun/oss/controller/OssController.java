package com.kun.oss.controller;

import com.kun.commonutils.R;
import com.kun.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file){
        //获取上传文件 MultipartFile
        //返回oos里路径
     String url= ossService.uploadFileAvatar(file);
        return  R.ok().data("url",url);
    }
}
