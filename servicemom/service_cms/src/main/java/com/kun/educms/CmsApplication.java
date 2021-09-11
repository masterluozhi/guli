package com.kun.educms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.kun"})
@SpringBootApplication
public class CmsApplication {
    public static void main(String[] args) {
      SpringApplication.run(CmsApplication.class,args);
    }
}
