package com.example.myproject.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @Email(message = ":please enter a valid email id",regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.(com|in)$")
    @NotBlank(message = ":email field should not be empty")
    private String mail;
    @NotBlank(message = ":please enter password")
    private String password;

}
