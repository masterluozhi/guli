package com.kun.eduservice.service;

import com.kun.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
public interface EDUVIDEOervice extends IService<EduVideo> {
 void  delVoideByCouseId(String courseId);
}
