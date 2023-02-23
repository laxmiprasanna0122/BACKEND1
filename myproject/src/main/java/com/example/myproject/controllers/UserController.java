package com.example.myproject.controllers;

import com.example.myproject.models.User;
import com.example.myproject.requests.UserLoginRequest;
import com.example.myproject.responses.ResponseMessage;
import com.example.myproject.responses.UserBalanceResponse;
import com.example.myproject.responses.UserLoginResponse;
import com.example.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequest userLoginRequest){

        String email = userLoginRequest.getMail();
        User user=userService.findByEmail(email);
        if(user==null){
            return ResponseEntity.badRequest().body(new ResponseMessage("no user found"));
        }
        else{
            if(user.getPassword().equals(userLoginRequest.getPassword())){
                return ResponseEntity.ok().body(new UserLoginResponse(user.getId(),user.getEmail()));
            }
            else{
                return ResponseEntity.badRequest().body(new ResponseMessage("password incorrect"));
            }
        }

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
