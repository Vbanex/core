package com.bank.core.account.entity;

import com.bank.core.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ===== Constructors =====
    public Account() {}

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getUser() {
        return user;
    }



    // ===== Setters =====
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }


}