package com.kun.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.commonutils.R;
import com.kun.eduservice.entity.EduCourse;
import com.kun.eduservice.entity.EduTeacher;
import com.kun.eduservice.service.EDUCOURSEervice;
import com.kun.eduservice.service.EDUTEACHERervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EDUCOURSEervice educoursEervice;
    @Autowired
    private EDUTEACHERervice eduteacheRervice;
    //查找热门老师和课程
    @GetMapping("/index")
    public R index(){
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByDesc("id");
        eduTeacherQueryWrapper.last("limit 8");
        List<EduTeacher> teacherlist = eduteacheRervice.list(eduTeacherQueryWrapper);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper .orderByDesc("id");
        wrapper .last("limit 8");
        List<EduCourse> courseslist = educoursEervice.list(wrapper);
        return R.ok().data("teacherlist",teacherlist).data("courseslist",courseslist);
    }
}
