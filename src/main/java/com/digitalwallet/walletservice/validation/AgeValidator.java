package com.digitalwallet.walletservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    private static final int MIN_AGE = 18;

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        if (dob == null) return false;
        return Period.between(dob, LocalDate.now()).getYears() >= MIN_AGE;
    }
}
