package com.igeek.egobuy.egobuyshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.igeek.egobuy.egobuyshop.mapper")
public class EgobuyShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgobuyShopApplication.class, args);
    }

}
