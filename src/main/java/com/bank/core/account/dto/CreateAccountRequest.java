package com.bank.core.account.dto;

import jakarta.validation.constraints.NotNull;

public class CreateAccountRequest {

    @NotNull
    private Long userId;

    // getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}