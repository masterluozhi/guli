package com.kun.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.eduservice.entity.EduSubject;
import com.kun.eduservice.entity.excel.SubjectData;
import com.kun.eduservice.service.EDUSUBJECTervice;
import com.kun.eduservice.service.impl.EduSubjectServiceImpl;
import com.kun.exceptionhandler.GuLiExceptionHandle;


public class SubjectListen extends AnalysisEventListener<SubjectData> {
    public EDUSUBJECTervice eduSubjectService;

    public  SubjectListen(){
    }

    public  SubjectListen(EDUSUBJECTervice eduSubjectService){
        this.eduSubjectService=eduSubjectService;
    }
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
     if(subjectData==null){
         throw new GuLiExceptionHandle(20001,"文件为空");
     }
        EduSubject eduSubject = this.selOne(eduSubjectService, subjectData.getOneSubjectName());
        if (eduSubject==null){
            eduSubject=new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
        }
        String pid = eduSubject.getId();
        EduSubject eduSubject1 = this.selTwo(eduSubjectService, subjectData.getTwoSubjectName(),pid);
        if (eduSubject1==null){
            eduSubject1=new EduSubject();
            eduSubject1.setParentId(pid);
            eduSubject1.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(eduSubject1);
        }

    }
  private EduSubject selOne(EDUSUBJECTervice eduSubjectService,String name){
      QueryWrapper queryWrapper = new QueryWrapper();
      queryWrapper.eq("title",name);
      queryWrapper.eq("parent_id","0");
      EduSubject one = eduSubjectService.getOne(queryWrapper);
      return one;
  }
    private EduSubject selTwo(EDUSUBJECTervice eduSubjectService,String name,String pid){
        QueryWrapper queryWrappe1r = new QueryWrapper();
         queryWrappe1r.eq("title",name);
        queryWrappe1r.eq("parent_id",pid);
        EduSubject one = eduSubjectService.getOne(queryWrappe1r);
        return one;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
