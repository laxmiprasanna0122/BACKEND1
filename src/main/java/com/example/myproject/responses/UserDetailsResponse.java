package com.example.myproject.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {


    private String email;


    private String password;


    private Float balance;


    private String userName;


    private Long contactNumber;


    private String accountNumber;

    public UserDetailsResponse(String userName, String email, String accountNumber,Long contactNumber ) {
    }
}
