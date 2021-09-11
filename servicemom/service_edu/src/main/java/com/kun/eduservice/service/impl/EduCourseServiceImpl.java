package com.kun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.eduservice.entity.*;
import com.kun.eduservice.entity.frontVo.CourseFrontVo;
import com.kun.eduservice.entity.frontVo.CourseVudeoVo;
import com.kun.eduservice.entity.vo.CourseInfoVo;
import com.kun.eduservice.entity.vo.CoursePublicVo;
import com.kun.eduservice.mapper.EduCourseMapper;
import com.kun.eduservice.service.EDUCHAPTERervice;
import com.kun.eduservice.service.EDUCOURSEDESCRIPTIONervice;
import com.kun.eduservice.service.EDUCOURSEervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun.eduservice.service.EDUVIDEOervice;
import com.kun.exceptionhandler.GuLiExceptionHandle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EDUCOURSEervice {
    @Autowired
    private EDUCOURSEDESCRIPTIONervice descriptionService;
@Autowired
    private EDUCHAPTERervice educhapteRervice;
    @Autowired
    private EDUVIDEOervice eduvideOervice;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //课程表添加信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if (insert <= 0) {
            throw new GuLiExceptionHandle(20001, "添加失败");
        }


        //课程简介表添加信息
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        descriptionService.save(eduCourseDescription);


        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = descriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updata(CourseInfoVo courseInfoVo) {


        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i == 0) {
            throw new GuLiExceptionHandle(20001, "修改课程失败");
        }
        //修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
          descriptionService.updateById(eduCourseDescription);


    }

    @Override
    public CoursePublicVo getPublicCourseInfo(String courseId) {
        CoursePublicVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    @Override
    public List<CoursePublicVo> getAllPublishCourseInfo() {
        List<CoursePublicVo> allPublishCourseInfo = baseMapper.getAllPublishCourseInfo();
        return allPublishCourseInfo;
    }

    @Override
    public void delAll(String courseId) {
        int i = baseMapper.deleteById(courseId);
        boolean b = descriptionService.removeById(courseId);
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",courseId);
        boolean remove = educhapteRervice.remove(eduChapterQueryWrapper);
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();

        eduvideOervice.delVoideByCouseId(courseId);


    }

    @Override
    public Map<String, Object> pageList(long page,long limit,CourseFrontVo courseFrontVo) {
       Page<EduCourse> eduCoursePage = new Page<>(page, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseFrontVo.getTitle();
        String teacherId = courseFrontVo.getTeacherId();
        String subjectId = courseFrontVo.getSubjectId();
        String subjectParentId = courseFrontVo.getSubjectParentId();
        Long buyCount = courseFrontVo.getBuyCount();

        Date gmtCreate = courseFrontVo.getGmtCreate();
        BigDecimal price = courseFrontVo.getPrice();

        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(buyCount)) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(price)) {
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(gmtCreate)) {
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id", teacherId);
        }
        baseMapper.selectPage(eduCoursePage,wrapper);

        List<EduCourse> records = eduCoursePage.getRecords();
        long current = eduCoursePage.getCurrent();
        long pages = eduCoursePage.getPages();
        long total = eduCoursePage.getTotal();
        long size = eduCoursePage.getSize();
        boolean hasNext = eduCoursePage.hasNext();
        boolean hasPrevious = eduCoursePage.hasPrevious();

        HashMap<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("current",current);
        map.put("total",total);
        map.put("size",size);
        map.put("pages",pages);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

    @Override
    public CourseVudeoVo getBaseCourseId(String id) {
        CourseVudeoVo baseCourseId = baseMapper.getBaseCourseId(id);
        return baseCourseId;
    }
}
