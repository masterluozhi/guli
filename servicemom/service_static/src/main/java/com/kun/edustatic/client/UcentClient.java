package com.kun.edustatic.client;

import com.kun.commonutils.R;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcentClient {
    @GetMapping("/educenter/countOneDay/{day}")
    public R countOneDay(@PathVariable("day") String day);
}
