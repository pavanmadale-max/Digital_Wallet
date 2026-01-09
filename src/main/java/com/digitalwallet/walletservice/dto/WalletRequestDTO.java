package com.digitalwallet.walletservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class WalletRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Initial balance is required")
    @Positive(message = "Initial balance must be greater than zero")
    private BigDecimal balance;

    // getters & setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
