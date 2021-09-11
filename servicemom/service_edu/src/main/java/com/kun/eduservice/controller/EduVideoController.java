package com.kun.eduservice.controller;


import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.kun.commonutils.R;
import com.kun.eduservice.client.VodClient;
import com.kun.eduservice.entity.EduVideo;
import com.kun.eduservice.service.EDUVIDEOervice;
import com.kun.exceptionhandler.GuLiExceptionHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author test.java
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {
 @Autowired
    private VodClient vodClient;
@Autowired
    private EDUVIDEOervice eduvideOervice;
@PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
  eduvideOervice.save(eduVideo);
       return  R.ok();
}
@DeleteMapping("delVideo/{videoId}")
    public  R delVideo(@PathVariable String videoId){
//nacos调用生产者
    EduVideo byId = eduvideOervice.getById(videoId);
    System.out.println(byId);
    String videoSourceId = byId.getVideoSourceId();
    System.out.println(videoSourceId);
    if(!StringUtils.isEmpty(videoSourceId)){
      vodClient.delById(videoSourceId);

    }


    eduvideOervice.removeById(videoId);
    return  R.ok();
}
@PostMapping("updataVideo")
    public R updataVideo(@RequestBody EduVideo eduVideo){
     eduvideOervice.updateById(eduVideo);
     return  R.ok();
}
@GetMapping("selVideoById/{videoId}")
    public  R selVideoByid(@PathVariable String videoId){
    EduVideo byId = eduvideOervice.getById(videoId);
    return R.ok().data("item",byId);
}
}

