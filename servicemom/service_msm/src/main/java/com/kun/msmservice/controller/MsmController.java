package com.kun.msmservice.controller;

import com.kun.commonutils.R;
import com.kun.commonutils.RandonUtils;
import com.kun.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin //跨域
@RestController
@RequestMapping("/edumsm")
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("sendSms/{mobile}")
    public R sendSms(@PathVariable String mobile){
        String code = redisTemplate.opsForValue().get(mobile);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //2、如果redis获取不到，进行阿里云发送

        //生成随机数，传递阿里云进行发送
        code = RandonUtils.getFourBitRandom();

        //调用service的方法，发送
        boolean b = msmService.senMsm(mobile,code );
        if (b) {
            //阿里云发送成功，把发送成功的验证码放入redis缓存中
            //设置有效时间
            redisTemplate.opsForValue().set(mobile,code,5, TimeUnit.MINUTES);
            return R.ok().message("短信发送成功");
        }else
            return R.error().message("短信发送失败");
    }
    }

