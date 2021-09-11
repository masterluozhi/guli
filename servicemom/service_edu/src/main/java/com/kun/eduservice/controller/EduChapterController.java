package com.kun.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.commonutils.R;
import com.kun.eduservice.entity.EduChapter;
import com.kun.eduservice.entity.chapter.ChapterVo;
import com.kun.eduservice.service.EDUCHAPTERervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EDUCHAPTERervice educhapteRervice;
    //课程大纲列表需要课程id进行查群
    @GetMapping("/getCgapterVideo/{courseId}")
    public R getAll(@PathVariable String courseId ){
        List<ChapterVo> chapterVideo = educhapteRervice.getChapterVideo(courseId);
        return  R.ok().data("item",chapterVideo);

    }
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
      educhapteRervice.save(eduChapter);
      return  R.ok();
    }
    @PostMapping("/selChapterById/{chapterId}")
    public R selChapterById(@PathVariable String chapterId){
        EduChapter byId = educhapteRervice.getById(chapterId);
        return  R.ok().data("item",byId);
    }
    @PostMapping("/updataChapter")
    public  R updataChapter(@RequestBody EduChapter eduChapter){
        educhapteRervice.updateById(eduChapter);
        return  R.ok();
    }
    @DeleteMapping("del/{chapterId}")
    public R delChapter(@PathVariable String chapterId){
           educhapteRervice.delChapter(chapterId);
        return  R.ok();
    }
    @GetMapping("getById/{chapterId}")
    public R getByid(@PathVariable String chapterId){
        EduChapter byId = educhapteRervice.getById(chapterId);
          return R.ok().data("item",byId);
    }
}

