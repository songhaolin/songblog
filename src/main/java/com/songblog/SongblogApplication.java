package com.songblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.songblog.mapper")
public class SongblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongblogApplication.class, args);
    }

}
