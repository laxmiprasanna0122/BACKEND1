package com.example.myproject.services;

import com.example.myproject.models.User;

public interface UserService {
    User findByEmail(String email);
    User findById(Long id);
    User getUserDetails(Long id);

    User findByAccountNumber(String accountNumber);
}
