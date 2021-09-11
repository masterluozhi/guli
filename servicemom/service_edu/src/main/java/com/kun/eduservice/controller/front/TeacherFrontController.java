package com.kun.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.commonutils.R;
import com.kun.eduservice.entity.EduCourse;
import com.kun.eduservice.entity.EduTeacher;
import com.kun.eduservice.service.EDUCOURSEervice;
import com.kun.eduservice.service.EDUTEACHERervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EDUTEACHERervice eduteacheRervice;
    @Autowired
    private EDUCOURSEervice educoursEervice;
    @PostMapping("/getTeacherFrontList/{page}/{limit}")
    public R  getTeacherFrontList(@PathVariable long page,@PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
       Map<String,Object> map=eduteacheRervice.getTeacherFrontList(teacherPage);
        //原生方法返回分页所有数据
        return  R.ok().data("data",map);
    }

    @GetMapping("getTeacherFrontInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable String id){
        //根据讲师id查询讲师信息
        EduTeacher teacher = eduteacheRervice.getById(id);
        //根据讲师id查询课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courselist = educoursEervice.list(wrapper);

        return  R.ok().data("teacher",teacher).data("courseList",courselist);
    }
}
