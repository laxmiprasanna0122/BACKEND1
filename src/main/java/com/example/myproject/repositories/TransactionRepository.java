package com.example.myproject.repositories;

import com.example.myproject.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    public List<Transaction> findAllBySender_Id(Long id);
    public List<Transaction> findAllByReceiver_Id(Long id);
}
