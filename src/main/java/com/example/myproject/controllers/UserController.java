package com.example.myproject.controllers;

import com.example.myproject.exceptions.ValidationException;
import com.example.myproject.helpers.CODE;
import com.example.myproject.helpers.Validation;
import com.example.myproject.models.User;
import com.example.myproject.requests.TransactionRequest;
import com.example.myproject.requests.UserLoginRequest;
import com.example.myproject.responses.Response;
import com.example.myproject.responses.ResponseMessage;
import com.example.myproject.responses.UserBalanceResponse;
import com.example.myproject.responses.UserLoginResponse;
import com.example.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    Validator validator;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest userLoginRequest){

        String email = userLoginRequest.getMail();
        User user=userService.findByEmail(email);

        Validation.validate(userLoginRequest, validator);

       return Validation.validateUser(user,userLoginRequest);

    }

    @GetMapping("/getbalance")
    public ResponseEntity getbalance(@RequestParam Long id){

        User user=userService.findById(id);

        return ResponseEntity.ok(new UserBalanceResponse(user.getBalance()));

    }
    @PostMapping("/getUserDetails")
    public ResponseEntity getUserDetailsWithPost(@RequestParam Long id){
        User user=userService.getUserDetails(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity getUserDetails(@RequestParam Long id){
        User user=userService.getUserDetails(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getUserDetailsByAccountNo")
    public ResponseEntity getUserDetails(@RequestParam String accountNumber){
        User user=userService.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(user);
    }

}
