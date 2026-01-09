package com.digitalwallet.walletservice.repository;

import com.digitalwallet.walletservice.entity.Transaction;
import com.digitalwallet.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Derived query
    List<Transaction> findBySenderWalletOrReceiverWallet(Wallet sender, Wallet receiver);

    // JPQL query
    @Query("""
        SELECT t FROM Transaction t
        WHERE t.amount > :amount
    """)
    List<Transaction> findTransactionsGreaterThan(BigDecimal amount);

    // Native query example
    @Query(value = """
        SELECT * FROM transactions
        WHERE sender_wallet_id = ?1 OR receiver_wallet_id = ?1
    """, nativeQuery = true)
    List<Transaction> findAllByWalletId(Long walletId);
}
