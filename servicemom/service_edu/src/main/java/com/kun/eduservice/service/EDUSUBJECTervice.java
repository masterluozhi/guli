package com.kun.eduservice.service;

import com.kun.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kun.eduservice.entity.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-08-21
 */
public interface EDUSUBJECTervice extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EDUSUBJECTervice edusubjecTervice);

    List<OneSubject> getAllSubject();
}
