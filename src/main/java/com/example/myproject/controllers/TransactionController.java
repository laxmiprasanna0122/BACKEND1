package com.example.myproject.controllers;

import com.example.myproject.exceptions.ValidationException;
import com.example.myproject.helpers.CODE;
import com.example.myproject.helpers.Validation;
import com.example.myproject.requests.TransactionRequest;
import com.example.myproject.requests.TransactionRequestAccNo;
import com.example.myproject.responses.Response;
import com.example.myproject.responses.ResponseMessage;
import com.example.myproject.responses.TransactionResponse;
import com.example.myproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/transaction")

public class TransactionController {

    @Autowired
    Validator validator;
    @Autowired
    TransactionService transactionService;

    @PostMapping("/createtransaction")
    public ResponseEntity createTransaction(@RequestBody @Valid TransactionRequest transactionRequest){

        try{
            transactionService.createTransaction(transactionRequest);
            return  ResponseEntity.ok().body(new ResponseMessage("created"));

        } catch (Exception e){
        return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
    }

    }

    @PostMapping("/createTransactionAccountNo")
    public ResponseEntity createTransactionAccountNo(@RequestBody @Valid TransactionRequestAccNo transactionRequestAccNo){
        try{
            transactionService.createTransactionAccNo(transactionRequestAccNo);
            return ResponseEntity.ok().body(new ResponseMessage("created"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
    }

    @GetMapping("/getalltransactions")
    public ResponseEntity createTransaction(@RequestParam Long id){

        List<TransactionResponse> transactionResponses=transactionService.findAllByUserId(id);
        return ResponseEntity.ok(transactionResponses);





    }



}
