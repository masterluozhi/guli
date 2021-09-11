package com.kun.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun.eduservice.entity.EduChapter;
import com.kun.eduservice.entity.EduVideo;
import com.kun.eduservice.entity.chapter.ChapterVo;
import com.kun.eduservice.entity.chapter.VideoVo;
import com.kun.eduservice.mapper.EduChapterMapper;
import com.kun.eduservice.service.EDUCHAPTERervice;
import com.kun.eduservice.service.EDUVIDEOervice;
import com.kun.exceptionhandler.GuLiExceptionHandle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EDUCHAPTERervice {
    @Autowired
    private EDUVIDEOervice eduvideOervice;

    @Override
    public List<ChapterVo> getChapterVideo(String coureId) {

        //根据课程id查询课程所有的章节
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id", coureId);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterQueryWrapper);
        //根据课程id查询课程所有的小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", coureId);
        List<EduVideo> eduVideos = eduvideOervice.list(videoQueryWrapper);
        //创建list集合，用于封装最后数据
        List<ChapterVo> finallList = new ArrayList<>();
        //遍历章节list集团封装
        for (int i = 0; i < eduChapters.size(); i++) {
            ChapterVo chapterVo = new ChapterVo();
            EduChapter eduChapter = eduChapters.get(i);
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finallList.add(chapterVo);
            List<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo eduVideo = eduVideos.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }

            }
            chapterVo.setChildren(videoVoList);
        }
        //遍历小节list集团封装


        return finallList;
    }

    @Override
    public boolean delChapter(String chapterId) {
        //根据章节id查询小节表
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("chapter_id", chapterId);
        int count = eduvideOervice.count(eduVideoQueryWrapper);
        if (count > 0) {
            throw new GuLiExceptionHandle(20001, "不能删除");
        } else {

            int i = baseMapper.deleteById(chapterId);
            return  i>0;
        }

    }
}
