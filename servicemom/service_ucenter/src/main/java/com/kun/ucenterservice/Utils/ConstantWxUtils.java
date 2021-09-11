package com.kun.ucenterservice.Utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ConstantWxUtils implements InitializingBean {
    @Value("${wx.open.app_id}")
    private String appId;
    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redireUrl;

    public static  String APPID;
    public static  String APPSECRET;
    public static  String REDIREURL;
    @Override
    public void afterPropertiesSet() throws Exception {
       APPID=appId;
       APPSECRET=appSecret;
       REDIREURL=redireUrl;
    }
}
