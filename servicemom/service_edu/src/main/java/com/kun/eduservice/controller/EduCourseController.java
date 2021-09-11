package com.kun.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.commonutils.CoursePublicOrder;
import com.kun.commonutils.R;
import com.kun.eduservice.client.OrderClient;
import com.kun.eduservice.entity.EduCourse;
import com.kun.eduservice.entity.EduCourseDescription;
import com.kun.eduservice.entity.EduTeacher;
import com.kun.eduservice.entity.vo.CourseInfoVo;
import com.kun.eduservice.entity.vo.CoursePublicVo;
import com.kun.eduservice.entity.vo.EduTeacherVo;
import com.kun.eduservice.service.EDUCOURSEDESCRIPTIONervice;
import com.kun.eduservice.service.EDUCOURSEervice;
import com.kun.eduservice.service.EDUTEACHERervice;
import com.kun.eduservice.service.impl.EduCourseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
@Api(description = "课程表控制")
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EDUCOURSEervice eduCourseService;
@Autowired
private EDUTEACHERervice eduteacheRervice;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    @GetMapping("selOneByid/{courseid}")
    public R selOneByid(@PathVariable String courseid) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseid);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @PostMapping("updata")
    public R updata(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updata(courseInfoVo);
        return R.ok();
    }

    @GetMapping("getPublicCourseInfo/{courseId}")
    public R getPublicCourseInfo(@PathVariable String courseId) {
        CoursePublicVo publicCourseInfo = eduCourseService.getPublicCourseInfo(courseId);
        return R.ok().data("item", publicCourseInfo);
    }

    @GetMapping("/getAllPublishCourseInfo")
    public R getAllPublishCourseInfo() {
        List<CoursePublicVo> allPublishCourseInfo = eduCourseService.getAllPublishCourseInfo();
        return R.ok().data("item", allPublishCourseInfo);

    }

    @ApiOperation("分页")
    @PostMapping("getCourseListPage/{current}/{limit}")
    public R pages(@ApiParam(name = "current", value = "当前页", example = "0") @PathVariable long current,
                   @ApiParam(name = "limit", value = "每页几个元素", example = "0") @PathVariable long limit,
                   @RequestBody(required = false) CourseInfoVo coursePublicVo) {


        Page<EduCourse> coursePublicVoPager = new Page<>(current, limit);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = coursePublicVo.getTitle();

        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
//执行查询

        eduCourseService.page(coursePublicVoPager, wrapper);

        long total = coursePublicVoPager.getTotal();

        List<EduCourse> records = coursePublicVoPager.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }

    @GetMapping("delAll/{courseId}")
    public R delAll(@PathVariable String courseId) {
        eduCourseService.delAll(courseId);
        return R.ok();
    }
        //根据课程id获得课程信息
    @GetMapping("getCourseInfo/{id}")
    public CoursePublicOrder getCourseInfo(@PathVariable String id){
        EduCourse byId = eduCourseService.getById(id);

        EduTeacher byId1 = eduteacheRervice.getById(byId.getTeacherId());

        CoursePublicOrder coursePublicOrder = new CoursePublicOrder();

         BeanUtils.copyProperties(byId,coursePublicOrder);
         coursePublicOrder.setTeacherName(byId1.getName());

         return  coursePublicOrder;
    }
}

