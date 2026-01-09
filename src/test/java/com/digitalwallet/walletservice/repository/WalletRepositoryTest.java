package com.digitalwallet.walletservice.repository;

import com.digitalwallet.walletservice.entity.User;
import com.digitalwallet.walletservice.entity.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class WalletRepositoryTest {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindWalletsByUserId() {
        User user = userRepository.save(
                new User(
                        "Wallet User",
                        "wallet@test.com",
                        "9000000010",
                        LocalDate.of(1995, 5, 10)
                )
        );

        walletRepository.save(new Wallet(user, BigDecimal.valueOf(1000), "INR"));
        walletRepository.save(new Wallet(user, BigDecimal.valueOf(2000), "INR"));

        List<Wallet> wallets =
                walletRepository.findByUserUserId(user.getUserId());

        assertThat(wallets).hasSize(2);
    }
}
