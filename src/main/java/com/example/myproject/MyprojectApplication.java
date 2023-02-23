package com.example.myproject;

import com.example.myproject.models.User;
import com.example.myproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MyprojectApplication {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void postConstruct(){

    }
    public static void main(String[] args) {
        SpringApplication.run(MyprojectApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/transaction/**").allowedOrigins("http://localhost:3000");
                registry.addMapping("/user/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

}
