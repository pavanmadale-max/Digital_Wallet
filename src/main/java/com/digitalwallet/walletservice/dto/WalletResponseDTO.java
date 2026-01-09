package com.digitalwallet.walletservice.dto;

import java.math.BigDecimal;

public class WalletResponseDTO {

    private long walletId;

    private long userId;

    private BigDecimal balance;

    private String currency;

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
