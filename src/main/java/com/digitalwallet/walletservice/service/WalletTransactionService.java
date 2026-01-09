package com.digitalwallet.walletservice.service;

import com.digitalwallet.walletservice.entity.Transaction;
import com.digitalwallet.walletservice.entity.TransactionRequest;
import com.digitalwallet.walletservice.entity.Wallet;
import com.digitalwallet.walletservice.exception.BusinessException;
import com.digitalwallet.walletservice.exception.ResourceNotFoundException;
import com.digitalwallet.walletservice.repository.TransactionRepository;
import com.digitalwallet.walletservice.repository.TransactionRequestRepository;
import com.digitalwallet.walletservice.repository.WalletRepository;
import com.digitalwallet.walletservice.validation.WalletBalanceValidator;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import java.math.BigDecimal;

@Service
public class WalletTransactionService {

    private static final Logger log =
            LoggerFactory.getLogger(WalletTransactionService.class);

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionRequestRepository requestRepository;

    public WalletTransactionService(
            WalletRepository walletRepository,
            TransactionRepository transactionRepository,
            TransactionRequestRepository requestRepository
    ) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.requestRepository = requestRepository;
    }

    // ---------------- CREDIT ----------------
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED
    )
    public void credit(Long walletId, BigDecimal amount, String requestId) {

        validateIdempotency(requestId);

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        wallet.credit(amount);

        transactionRepository.save(
                Transaction.credit(wallet, amount)
        );

        log.info("Credited {} to wallet {}", amount, walletId);
    }

    // ---------------- DEBIT ----------------
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED
    )
    public void debit(Long walletId, BigDecimal amount, String requestId) {

        validateIdempotency(requestId);

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        WalletBalanceValidator.validateSufficientBalance(wallet, amount);

        wallet.debit(amount);

        transactionRepository.save(
                Transaction.debit(wallet, amount)
        );

        log.info("Debited {} from wallet {}", amount, walletId);
    }

    // ---------------- TRANSFER ----------------
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.SERIALIZABLE
    )
    public void transfer(
            Long fromWalletId,
            Long toWalletId,
            BigDecimal amount,
            String requestId
    ) {

        validateIdempotency(requestId);

        Wallet sender = walletRepository.findById(fromWalletId)
                .orElseThrow(() -> new ResourceNotFoundException("Sender wallet not found"));

        Wallet receiver = walletRepository.findById(toWalletId)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver wallet not found"));

        WalletBalanceValidator.validateSufficientBalance(sender, amount);

        sender.debit(amount);
        receiver.credit(amount);

        transactionRepository.save(
                Transaction.transfer(sender, receiver, amount)
        );

        log.info("Transferred {} from {} to {}", amount, fromWalletId, toWalletId);
    }

    // ---------------- IDEMPOTENCY ----------------
    private void validateIdempotency(String requestId) {
        if (requestRepository.existsByRequestId(requestId)) {
            throw new BusinessException("Duplicate transaction request");
        }
        requestRepository.save(new TransactionRequest(requestId));
    }
}
