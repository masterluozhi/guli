package com.kun.eduorder.client;

import com.kun.commonutils.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClienr {
    @GetMapping("/educenter/getUserInfoByid/{id}")
    public UcenterMemberOrder getUserInfoByid(@PathVariable("id") String id);
}
