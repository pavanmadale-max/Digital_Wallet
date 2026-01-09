package com.digitalwallet.walletservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequestDTO(

        @NotNull Long fromWalletId,
        @NotNull Long toWalletId,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        String requestId
) {}
