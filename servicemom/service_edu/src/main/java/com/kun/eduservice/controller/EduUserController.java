package com.kun.eduservice.controller;

import com.kun.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "用户管理功能")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduUserController {
    @RequestMapping ("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admin").data("avatar","https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
    }
}
