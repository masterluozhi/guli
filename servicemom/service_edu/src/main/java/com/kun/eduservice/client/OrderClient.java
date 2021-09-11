package com.kun.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-order")
@Component
public interface OrderClient {
    @GetMapping("/eduorder/torder/selIsBuy/{courseId}/{memberId}")
    public  boolean selIsBuy(@PathVariable String courseId, @PathVariable String memberId);
}
