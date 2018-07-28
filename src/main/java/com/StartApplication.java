package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 */
@MapperScan("com.easybuild.cores.dao")
@SpringBootApplication(scanBasePackages = "com")
public class StartApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
