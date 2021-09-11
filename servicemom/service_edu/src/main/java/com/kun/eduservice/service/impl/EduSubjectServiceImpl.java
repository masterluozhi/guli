package com.kun.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.eduservice.entity.EduSubject;
import com.kun.eduservice.entity.OneSubject;
import com.kun.eduservice.entity.TwiSubject;
import com.kun.eduservice.entity.excel.SubjectData;
import com.kun.eduservice.listener.SubjectListen;
import com.kun.eduservice.mapper.EduSubjectMapper;
import com.kun.eduservice.service.EDUSUBJECTervice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-08-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EDUSUBJECTervice {

    @Override
    public void saveSubject(MultipartFile file, EDUSUBJECTervice edusubjecTervice) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectListen(edusubjecTervice)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllSubject() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper();
        //查询一级分类
        queryWrapper.eq("parent_id", "0");
        List<EduSubject> one = baseMapper.selectList(queryWrapper);
        //查询二级分类
        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper();
        queryWrapper1.ne("parent_id", "0");
        List<EduSubject> two = baseMapper.selectList(queryWrapper1);
        //遍历
        List<OneSubject> oneSubjects = new ArrayList<>();
        for (int i = 0; i < one.size(); i++) {
            EduSubject eduSubject = one.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);

            oneSubjects.add(oneSubject);

            List<TwiSubject> twiSubjects = new ArrayList<>();
            for (int y = 0; y < two.size(); y++) {
                EduSubject eduSubjectTwo = two.get(y);

                if (eduSubjectTwo.getParentId().equals(eduSubject.getId())) {
                    TwiSubject twiSubject = new TwiSubject();
                    BeanUtils.copyProperties(eduSubjectTwo, twiSubject);
                    twiSubjects.add(twiSubject);
                }

            }
            oneSubject.setChildren(twiSubjects);
        }

        return oneSubjects;
    }
}