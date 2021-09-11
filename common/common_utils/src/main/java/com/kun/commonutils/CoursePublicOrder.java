package com.kun.commonutils;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePublicOrder {
    private  String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String oneSubject;
    private String twoSubject;
    private  String teacherName;
    private BigDecimal price;//只用于显示
}
