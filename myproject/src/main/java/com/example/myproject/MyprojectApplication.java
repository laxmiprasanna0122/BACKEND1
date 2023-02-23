package com.example.myproject;

import com.example.myproject.models.User;
import com.example.myproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MyprojectApplication {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void postConstruct(){
        User user1=new User(1L,"test1@gmail.com","test1",200F,"user1",9874567890L,"A12345", null);
        User user2=new User(2L,"test2@gmail.com","test2",400F,"user2",8909876543L,"B12345", null);
        userRepository.save(user1);
        userRepository.save(user2);}
    public static void main(String[] args) {
        SpringApplication.run(MyprojectApplication.class, args);
    }

}
