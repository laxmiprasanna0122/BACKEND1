package com.example.myproject.services;

import com.example.myproject.requests.TransactionRequest;
import com.example.myproject.requests.TransactionRequestAccNo;
import com.example.myproject.responses.TransactionResponse;

import java.util.List;

public interface TransactionService {

    void createTransaction(TransactionRequest transactionRequest) throws Exception;
    void createTransactionAccNo(TransactionRequestAccNo transactionRequestAccNo) throws Exception;

    List<TransactionResponse> findAllByUserId(Long id);
}
