package com.kun.vodservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.kun.commonutils.R;
import com.kun.exceptionhandler.GuLiExceptionHandle;
import com.kun.vodservice.service.EduVodServie;
import com.kun.vodservice.utils.Constant;
import com.kun.vodservice.utils.InitObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/eduvod")
@CrossOrigin
public class VodController {
    @Autowired
    private EduVodServie eduVodServie;
@PostMapping("/uploadVod")
    public R  uploadVod(@RequestParam(value = "file",required = false)MultipartFile file) throws IOException {
     String videoId =  eduVodServie.uploadVod(file);
    return R.ok().data("videoId",videoId);
}
@DeleteMapping("delByid/{vid}")
    public  R delById(@PathVariable String vid) {
        try {
            DefaultAcsClient client = InitObject.initVodClient(Constant.ACCESS_KEY_ID, Constant.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(vid);
            client.getAcsResponse(request);
           return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuLiExceptionHandle(20001,"删除失败");
        }
    }
    @DeleteMapping("/delAll")
    public R delAll(@RequestParam("videoList")List<String> vodIdList ){
               eduVodServie.removeAll(vodIdList);
               return  R.ok();
    }
    @GetMapping("/getPlayAuth/{vid}")
      public R     getPlayAuth(@PathVariable String vid){
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(vid);
        DefaultAcsClient client = null;
        try {
            client = InitObject.initVodClient("LTAI5t9TjA8xcrfu5din929m", "bQRQYDLAgdS1PSGb7oc6VaM5OXZ0g4");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
        response = client.getAcsResponse(request);
        //播放凭证
        System.out.print("Pla  DefaultAcsClient client = InitObject.initVodClient(\"LTAI5t9TjA8xcrfu5din929m\", \"bQRQYDLAgdS1PSGb7oc6VaM5OXZ0g4\");yAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
    } catch (Exception e) {
        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
    }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
        return R.ok().data("Auth",response.getPlayAuth());
}

    }

