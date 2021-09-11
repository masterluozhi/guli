package com.kun.eduservice.service;

import com.kun.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kun.eduservice.entity.frontVo.CourseFrontVo;
import com.kun.eduservice.entity.frontVo.CourseVudeoVo;
import com.kun.eduservice.entity.vo.CourseInfoVo;
import com.kun.eduservice.entity.vo.CoursePublicVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
public interface EDUCOURSEervice extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updata(CourseInfoVo courseInfoVo);
    CoursePublicVo getPublicCourseInfo(String courseId);

    List<CoursePublicVo> getAllPublishCourseInfo();

    void delAll(String courseId);

    Map<String,Object> pageList(long page,long limit,CourseFrontVo courseFrontVo);

    CourseVudeoVo getBaseCourseId(String id);
}
