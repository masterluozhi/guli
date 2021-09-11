package com.kun.eduservice.entity.vo;

import lombok.Data;

@Data
public class CoursePublicVo {
    private  String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String oneSubject;
    private String twoSubject;
    private  String name;
    private String price;//只用于显示
}
