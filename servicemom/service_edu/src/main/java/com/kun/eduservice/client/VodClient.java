package com.kun.eduservice.client;

import com.kun.commonutils.R;
import com.kun.eduservice.entity.EduVideo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="service-vod")
@Component
public interface VodClient {
    //必须加pathbariable注解
    @DeleteMapping("/eduvod/delByid/{vid}")
    R delById(@PathVariable("vid") String vid);
    //必须在list中定义泛型
    @DeleteMapping("/eduvod/delAll")
     R delAll(@RequestParam("videoList")List<String> vodIdList );

}
