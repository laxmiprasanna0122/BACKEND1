package com.example.myproject.services;

import com.example.myproject.models.User;
import com.example.myproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override

    public User findByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override

    public User findById(Long id) {
        User user=userRepository.findById(id).get();
        return user;
    }

    @Override

    public User getUserDetails(Long id) {
        return userRepository.findById(id).get();
    }

    @Override

    public User findByAccountNumber(String accountNumber) {
        User user=userRepository.findByAccountNumber(accountNumber);
        return user;

    }

}
