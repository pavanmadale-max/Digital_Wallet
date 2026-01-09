package com.digitalwallet.walletservice.repository;

import com.digitalwallet.walletservice.entity.TransactionRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRequestRepository
        extends JpaRepository<TransactionRequest, Long> {

    boolean existsByRequestId(String requestId);
}
