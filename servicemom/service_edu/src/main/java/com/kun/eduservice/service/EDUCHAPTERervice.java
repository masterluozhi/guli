package com.kun.eduservice.service;

import com.kun.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kun.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
public interface EDUCHAPTERervice extends IService<EduChapter> {

   List<ChapterVo> getChapterVideo(String coureId);

   boolean delChapter(String chapterId);
}
