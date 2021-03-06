package com.kun.vodservice.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.kun.exceptionhandler.GuLiExceptionHandle;
import com.kun.vodservice.utils.Constant;
import com.kun.vodservice.utils.InitObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class EduVodServiceImpl implements EduVodServie {
    @Override
    public String uploadVod(MultipartFile multipartFile) throws IOException {
        String keyid = Constant.ACCESS_KEY_ID;
        String keySecret = Constant.ACCESS_KEY_SECRET;
        String fileName = multipartFile.getOriginalFilename();
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        InputStream inputStream = multipartFile.getInputStream();

        UploadStreamRequest request = new UploadStreamRequest(keyid, keySecret, title, fileName, inputStream);
        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        UploadStreamResponse response = uploadVideo.uploadStream(request);
        String videoId = response.getVideoId();
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return videoId;
    }

    @Override
    public void removeAll(List vodIdList) {
        try {
            DefaultAcsClient client = InitObject.initVodClient(Constant.ACCESS_KEY_ID, Constant.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
//apache下的stringUtils方法
            String join = StringUtils.join(vodIdList.toArray(), ",");
            request.setVideoIds(join );
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuLiExceptionHandle(20001,"删除失败");
        }
    }
}

