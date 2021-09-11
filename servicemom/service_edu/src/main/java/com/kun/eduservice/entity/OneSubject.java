package com.kun.eduservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private  String id;
    private  String title;
    private List<TwiSubject> children = new ArrayList();
}
