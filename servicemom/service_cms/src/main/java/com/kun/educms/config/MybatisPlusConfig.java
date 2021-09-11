package com.kun.educms.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

    @Configuration
    @MapperScan("com.kun.educms.mapper")
    public class MybatisPlusConfig {


        @Bean
        @Profile({"dev","test"})
        public PerformanceInterceptor performanceInterceptor(){
            PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
            performanceInterceptor.setMaxTime(1000);
            performanceInterceptor.setFormat(true);
            return performanceInterceptor;
        }
        @Bean
        public ISqlInjector sqlInjector(){
            return  new LogicSqlInjector();

        }

        @Bean
        public PaginationInterceptor paginationInterceptor(){
            return  new PaginationInterceptor();
        }

    }


