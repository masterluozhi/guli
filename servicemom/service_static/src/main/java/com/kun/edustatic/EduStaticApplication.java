package com.kun.edustatic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.kun")
@MapperScan("com.kun.edustatic.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
public class EduStaticApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduStaticApplication.class,args);
    }
}
