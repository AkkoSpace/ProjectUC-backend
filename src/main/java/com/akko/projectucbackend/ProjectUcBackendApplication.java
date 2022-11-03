package com.akko.projectucbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author akko
 */
@SpringBootApplication
@MapperScan("com.akko.projectucbackend.mapper")
public class ProjectUcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectUcBackendApplication.class, args);
    }

}
