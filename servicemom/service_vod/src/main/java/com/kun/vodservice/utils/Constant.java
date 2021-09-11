package com.kun.vodservice.utils;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant implements InitializingBean {
    @Value("${aliyun.vod.flie.keyid}")
    private String keyid;
    @Value("${aliyun.vod.flie.keySecret}")
    private  String keysecret;
    public  static  String ACCESS_KEY_SECRET;
    public  static  String ACCESS_KEY_ID;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_SECRET=keysecret;
        ACCESS_KEY_ID=keyid;
    }
}
