package com.example.myproject.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @Email(message = ":please enter a valid email id",regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.(com|in)$")
    @NotBlank(message = ":email field should not be empty")
    private String emailReceiver;

    @Email(message = ":please enter a valid email id",regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+\\.(com|in)$")
    @NotBlank(message = ":email field should not be empty")
    private String emailSender;

    @NotNull(message = ":please enter amount greater than or equal to 1 RS")
    @Min(value = 1)
    private Float amount;
}
