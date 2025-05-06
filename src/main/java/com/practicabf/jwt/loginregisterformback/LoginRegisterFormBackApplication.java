package com.practicabf.jwt.loginregisterformback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LoginRegisterFormBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginRegisterFormBackApplication.class, args);
    }

}
