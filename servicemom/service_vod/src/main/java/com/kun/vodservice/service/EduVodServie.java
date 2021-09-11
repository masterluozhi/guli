package com.kun.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EduVodServie {
    String  uploadVod(MultipartFile multipartFile) throws IOException;


    void removeAll(List vodIdList);
}
