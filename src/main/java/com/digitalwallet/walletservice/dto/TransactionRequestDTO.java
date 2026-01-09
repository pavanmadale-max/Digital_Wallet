package com.digitalwallet.walletservice.dto;

import com.digitalwallet.walletservice.validation.ValidTransactionAmount;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionRequestDTO {

    @NotNull(message = "Sender wallet id is required")
    private Long senderWalletId;

    @NotNull(message = "Receiver wallet id is required")
    private Long receiverWalletId;

    @NotNull(message = "Transaction amount is required")
    @Positive(message = "Transaction amount must be greater than zero")
    @ValidTransactionAmount   //  custom validator
    private BigDecimal amount;

    private String description;

    // getters & setters
    public Long getSenderWalletId() {
        return senderWalletId;
    }

    public void setSenderWalletId(Long senderWalletId) {
        this.senderWalletId = senderWalletId;
    }

    public Long getReceiverWalletId() {
        return receiverWalletId;
    }

    public void setReceiverWalletId(Long receiverWalletId) {
        this.receiverWalletId = receiverWalletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
