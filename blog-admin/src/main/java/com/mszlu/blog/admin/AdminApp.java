package com.mszlu.blog.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author sfChampion
 * @date 2023/2/10 13:16
 */

@SpringBootApplication
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }
}
