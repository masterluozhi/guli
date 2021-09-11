package com.kun.msmservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@ConfigurationProperties(prefix = "tengxun.sms")
public class ConstantUtils implements InitializingBean {
    //腾讯云账户密钥对: SecretID

    private String secretId;

    //腾讯云账户密钥对: SecretKey

    private String secretKey;

    //SdkAppid

    private String appid;

//    //signName :签名
//    public static final String signName = "Sam先生";
//    //短信模板id:

private  String templateID;


    public static  String SECRETID;
    public static String SECRETKEY;
    public static  String SDKAPPID;
    public static  String TEMPLATEID;
    @Override
    public void afterPropertiesSet() throws Exception {
         SECRETID =this.secretId;
         SECRETKEY=this.secretKey;
         SDKAPPID=this.appid;
         TEMPLATEID=this.templateID;
    }
}
