package com.digitalwallet.walletservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WalletAmountRequestDTO(

        @NotNull(message = "Wallet id is required")
        Long walletId,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotNull(message = "Request id is required")
        String requestId
) {}
