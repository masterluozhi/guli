package com.kun.vodservice;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;

import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

public class TestVod {

//    /*获取播放地址函数*/
//    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
//        GetPlayInfoRequest request = new GetPlayInfoRequest();
//        request.setVideoId("62250a2119a543ec91570a8ad224631a");
//        return client.getAcsResponse(request);
//    }
//
//    /*以下为调用示例*/
//    public static void main(String[] argv) throws ClientException {
//        DefaultAcsClient client = InitObject.initVodClient("LTAI5t9TjA8xcrfu5din929m", "bQRQYDLAgdS1PSGb7oc6VaM5OXZ0g4");
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
//        try {
//            response = getPlayInfo(client);
//            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
//            //播放地址
//            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
//            }
//            //Base信息
//            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
//    }
public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
    request.setVideoId("62250a2119a543ec91570a8ad224631a");
    return client.getAcsResponse(request);
}

    /*以下为调用示例*/
    public static void main(String[] argv) throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t9TjA8xcrfu5din929m", "bQRQYDLAgdS1PSGb7oc6VaM5OXZ0g4");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("Pla  DefaultAcsClient client = InitObject.initVodClient(\"LTAI5t9TjA8xcrfu5din929m\", \"bQRQYDLAgdS1PSGb7oc6VaM5OXZ0g4\");yAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
