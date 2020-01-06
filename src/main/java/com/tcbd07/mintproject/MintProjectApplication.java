package com.tcbd07.mintproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.tcbd07.mintproject.dao")
@EnableSwagger2
@EnableRabbit
public class MintProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MintProjectApplication.class, args);
    }

}
