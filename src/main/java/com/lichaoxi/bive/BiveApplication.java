package com.lichaoxi.bive;

import com.lichaoxi.bive.security.CustomUserDetailsService;
import com.lichaoxi.bive.utils.SecurityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BiveApplication {

    public static void main(String[] args) {


        SpringApplication.run(BiveApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityUtils securityUtils() {
        return new SecurityUtils();
    }
}
