package com.digitalwallet.walletservice.validation;

import com.digitalwallet.walletservice.entity.Wallet;
import com.digitalwallet.walletservice.exception.BusinessException;

import java.math.BigDecimal;

public class WalletBalanceValidator {

    public static void validateSufficientBalance(Wallet wallet, BigDecimal amount) {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Insufficient wallet balance");
        }
    }
}
 