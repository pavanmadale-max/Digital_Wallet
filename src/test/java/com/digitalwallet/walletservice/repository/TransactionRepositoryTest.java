package com.digitalwallet.walletservice.repository;

import com.digitalwallet.walletservice.entity.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EntityManager entityManager;

    // ---------- helper methods (NO new Entity()) ----------

    private User persistUser(String email, String phone) {
        User user = entityManager.getReference(User.class, 0L);

        entityManager.persist(
                entityManager.merge(
                        ReflectionUtil.createUser(email, phone)
                )
        );
        return user;
    }

    private Wallet persistWallet(User user) {
        Wallet wallet = ReflectionUtil.createWallet(user);
        entityManager.persist(wallet);
        return wallet;
    }

    private Transaction persistTransaction(
            Wallet sender,
            Wallet receiver,
            BigDecimal amount
    ) {
        Transaction tx = ReflectionUtil.createTransaction(sender, receiver, amount);
        entityManager.persist(tx);
        return tx;
    }

    // ---------- TESTS ----------

    @Test
    void shouldFindTransactionsGreaterThan() {
        User user = ReflectionUtil.createUser("a@test.com", "9000000011");
        entityManager.persist(user);

        Wallet wallet = ReflectionUtil.createWallet(user);
        entityManager.persist(wallet);

        persistTransaction(wallet, wallet, BigDecimal.valueOf(100));
        persistTransaction(wallet, wallet, BigDecimal.valueOf(500));

        List<Transaction> result =
                transactionRepository.findTransactionsGreaterThan(BigDecimal.valueOf(200));

        assertThat(result).hasSize(1);
    }

    @Test
    void shouldFindTransactionsBySenderOrReceiverWallet() {
        User user = ReflectionUtil.createUser("b@test.com", "9000000022");
        entityManager.persist(user);

        Wallet wallet = ReflectionUtil.createWallet(user);
        entityManager.persist(wallet);

        persistTransaction(wallet, wallet, BigDecimal.valueOf(300));
        persistTransaction(wallet, wallet, BigDecimal.valueOf(600));

        List<Transaction> result =
                transactionRepository.findBySenderWalletOrReceiverWallet(wallet, wallet);

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldFindAllByWalletId() {
        User user = ReflectionUtil.createUser("c@test.com", "9000000033");
        entityManager.persist(user);

        Wallet wallet = ReflectionUtil.createWallet(user);
        entityManager.persist(wallet);

        persistTransaction(wallet, wallet, BigDecimal.valueOf(400));

        List<Transaction> result =
                transactionRepository.findAllByWalletId(wallet.getWalletId());

        assertThat(result).hasSize(1);
    }
}
