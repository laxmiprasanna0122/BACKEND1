package com.example.myproject.services;

import com.example.myproject.models.Transaction;
import com.example.myproject.models.User;
import com.example.myproject.repositories.TransactionRepository;
import com.example.myproject.repositories.UserRepository;
import com.example.myproject.requests.TransactionRequest;
import com.example.myproject.requests.TransactionRequestAccNo;
import com.example.myproject.responses.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    UserService userService;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;



    @Override
    @Transactional
    public void createTransaction(TransactionRequest transactionRequest) throws Exception {


        User sender =userService.findByEmail(transactionRequest.getEmailSender());
        User receiver =userService.findByEmail(transactionRequest.getEmailReceiver());
        if(receiver==null){
            throw new Exception("receiver Email is not found");
        }

        if(receiver.getId().equals(sender.getId())){
            throw new Exception("change the receiver email");
        }
        if(sender.getBalance() < transactionRequest.getAmount()){
            throw new Exception("Insuffisant Balance");
        }

        Transaction transaction=new Transaction();
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setAmount(transactionRequest.getAmount());

        receiver.setBalance(receiver.getBalance()+transaction.getAmount());
        sender.setBalance(sender.getBalance()-transaction.getAmount());


        transactionRepository.save(transaction);


    }
    // not using
    @Override
    @Transactional
    public void createTransactionAccNo( TransactionRequestAccNo transactionRequestAccNo) throws Exception {
        User sender =userService.findByAccountNumber(transactionRequestAccNo.getAccSender());
        User receiver=userService.findByAccountNumber(transactionRequestAccNo.getAccReceiver());


        Transaction transaction=new Transaction();
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setAmount(transactionRequestAccNo.getAmount());

        receiver.setBalance(receiver.getBalance()+transaction.getAmount());
        sender.setBalance(sender.getBalance()-transaction.getAmount());


        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public List<TransactionResponse> findAllByUserId(Long id) {
        List<Transaction> results=transactionRepository.findAllByReceiver_Id(id);
        List<Transaction> transactionsSenderList=transactionRepository.findAllBySender_Id(id);


        results.addAll(transactionsSenderList);
        List<TransactionResponse> transactionResponses=new ArrayList<>();
        results.forEach(transaction -> {
            TransactionResponse transactionResponse=new TransactionResponse(transaction.getId(),transaction.getAmount(),transaction.getCreatedAt(),transaction.getSender().getEmail(),transaction.getReceiver().getEmail());
            transactionResponses.add(transactionResponse);
        });


        return transactionResponses;
    }

}
