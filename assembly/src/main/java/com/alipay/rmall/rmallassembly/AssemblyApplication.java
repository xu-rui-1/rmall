package com.alipay.rmall.rmallassembly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.alipay.rmall"})
@MapperScan(value = "com.alipay.rmall.dal.dao")
public class AssemblyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssemblyApplication.class, args);
    }

}
