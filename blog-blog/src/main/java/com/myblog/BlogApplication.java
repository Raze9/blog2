package com.myblog;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;

@SpringBootApplication
@MapperScan("com.myblog.mapper")
@EnableScheduling
@EnableSwagger2
public class BlogApplication {
    public static void main(String[] args){
        SpringApplication.run(BlogApplication.class,args);
    }
}
