package com.kun.eduservice.controller;


import com.kun.commonutils.R;
import com.kun.eduservice.entity.OneSubject;
import com.kun.eduservice.service.EDUSUBJECTervice;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-08-21
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EDUSUBJECTervice edusubjecTervice;
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        edusubjecTervice.saveSubject(file,edusubjecTervice);
        return  R.ok();
    }
    //树形结果课程列表
    @GetMapping("getAllSubject")
    public  R getAllSubject(){
        List<OneSubject> allSubject = edusubjecTervice.getAllSubject();
        return  R.ok().data("list",allSubject);
    }
}

