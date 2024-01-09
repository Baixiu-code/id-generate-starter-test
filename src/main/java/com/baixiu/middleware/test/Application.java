package com.baixiu.middleware.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

/**
 * test application
 * @author baixiu
 * @date 创建时间 2024/1/2 11:49 AM
 */
@PropertySource(value = {
        "classpath:spring-datasource.properties",
}, encoding = "utf-8")
@SpringBootApplication(scanBasePackages={"com.baixiu.middleware"})
@MapperScan("com.baixiu.middleware.id.dao")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        System.out.println ("id middleware.test started");
    }
    
    
}
