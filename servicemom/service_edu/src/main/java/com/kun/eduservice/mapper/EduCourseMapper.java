package com.kun.eduservice.mapper;

import com.kun.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kun.eduservice.entity.frontVo.CourseVudeoVo;
import com.kun.eduservice.entity.vo.CoursePublicVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublicVo getPublishCourseInfo(String courseId);
    List<CoursePublicVo> getAllPublishCourseInfo();
    CourseVudeoVo getBaseCourseId(String id);
}
