package com.example.myproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private Float balance;

    @Column
    private String userName;

    @Column
    private Long contactNumber;

    @Column(unique = true)
    private String accountNumber;

    @OneToMany(fetch = FetchType.EAGER )
    private List<Transaction> transactions;



}
