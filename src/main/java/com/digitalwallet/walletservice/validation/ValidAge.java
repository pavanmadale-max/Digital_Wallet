package com.digitalwallet.walletservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "User must be at least 18 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
