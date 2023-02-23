package com.example.myproject.controllers;

import com.example.myproject.requests.TransactionRequest;
import com.example.myproject.requests.TransactionRequestAccNo;
import com.example.myproject.responses.ResponseMessage;
import com.example.myproject.responses.TransactionResponse;
import com.example.myproject.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:3000/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/createtransaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequest transactionRequest){


        try{
            transactionService.createTransaction(transactionRequest);
            return ResponseEntity.ok().body(new ResponseMessage("created"));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }

    }

    @PostMapping("/createTransactionAccountNo")
    public ResponseEntity createTransactionAccountNo(@RequestBody TransactionRequestAccNo transactionRequestAccNo){
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
