package com.kun.eduorder.client;

import com.kun.commonutils.CoursePublicOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface EduClient {
    @GetMapping("/eduservice/edu-course/getCourseInfo/{id}")
    public CoursePublicOrder getCourseInfo(@PathVariable("id") String id);
}
