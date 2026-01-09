package com.digitalwallet.walletservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class TransactionAmountValidator
        implements ConstraintValidator<ValidTransactionAmount, BigDecimal> {

    private static final BigDecimal MIN = BigDecimal.ONE;
    private static final BigDecimal MAX = new BigDecimal("100000");

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) return true; // @NotNull handles this

        return value.compareTo(MIN) >= 0 && value.compareTo(MAX) <= 0;
    }
}
