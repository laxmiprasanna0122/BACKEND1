package com.example.myproject.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestAccNo {
    private String accReceiver;
    private String accSender;
    private Float amount;
}
