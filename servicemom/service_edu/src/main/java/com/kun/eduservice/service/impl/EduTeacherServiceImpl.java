package com.kun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kun.eduservice.entity.EduTeacher;
import com.kun.eduservice.mapper.EduTeacherMapper;
import com.kun.eduservice.service.EDUTEACHERervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-08-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EDUTEACHERervice {

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByDesc("id");
         baseMapper.selectPage(teacherPage,eduTeacherQueryWrapper);
        //获得分页数据 翻进去集合
        List<EduTeacher> records = teacherPage.getRecords();
        long current= teacherPage.getCurrent();
        long total = teacherPage.getTotal();
        long size = teacherPage.getSize();
        long pages = teacherPage.getPages();
         boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("current",current);
        map.put("pages",pages);
        map.put("total",total);
        map.put("size",size);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);


        return map;
    }
}
