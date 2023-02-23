package com.example.myproject.helpers;

import com.example.myproject.exceptions.ValidationException;
import com.example.myproject.requests.TransactionRequest;
import com.example.myproject.requests.UserLoginRequest;
import com.example.myproject.responses.Response;
import com.example.myproject.models.User;
import com.example.myproject.responses.ResponseMessage;
import com.example.myproject.responses.UserLoginResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class Validation {
    public static void validate(TransactionRequest transactionRequest, Validator validator){
        Set<ConstraintViolation<TransactionRequest>> violations = validator.validate(transactionRequest);
        String message ="";
        if (!violations.isEmpty()) {

            for (ConstraintViolation violation : violations){
                message+=violation.getPropertyPath()+" "+ violation.getMessage() + " ,";
            }
            ConstraintViolation constraintViolation= violations.stream().findFirst().get();
            throw new ValidationException(message);
        }
    }

    public static void validate(UserLoginRequest userLoginRequest, Validator validator){
        Set<ConstraintViolation<UserLoginRequest>> violations = validator.validate(userLoginRequest);
        String message ="";
        if (!violations.isEmpty()) {

            for (ConstraintViolation violation : violations){
                message+=violation.getPropertyPath()+" "+ violation.getMessage() + " ,";
            }
            ConstraintViolation constraintViolation= violations.stream().findFirst().get();
            throw new ValidationException(message);
        }
    }

    public static ResponseEntity validateUser(User user,UserLoginRequest userLoginRequest){
        if(user==null){
            Response<Object> response = Response.builder()
                    .message("no user found")
                    .code(CODE.BAD_REQUEST.getId())
                    .success(true)
                    .build();
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(user.getPassword().equals(userLoginRequest.getPassword())){
            return ResponseEntity.ok().body(new UserLoginResponse(user.getId(),user.getEmail()));
        }
        else{
            return ResponseEntity.badRequest().body(new ResponseMessage("password incorrect"));
        }
    }

}
