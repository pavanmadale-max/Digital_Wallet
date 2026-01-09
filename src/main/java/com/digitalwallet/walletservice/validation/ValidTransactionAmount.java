package com.digitalwallet.walletservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TransactionAmountValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTransactionAmount {

    String message() default "Transaction amount must be between 1 and 100000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
