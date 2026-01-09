package com.digitalwallet.walletservice.repository;

import com.digitalwallet.walletservice.entity.User;
import com.digitalwallet.walletservice.entity.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUser() {
        User user = new User(
                "Pavan Madale",
                "pavan@test.com",
                "9000000001",
                LocalDate.of(2000, 1, 1)
        );

        User saved = userRepository.save(user);

        Optional<User> found = userRepository.findById(saved.getUserId());

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("pavan@test.com");
    }

    @Test
    void shouldCountAllUsers() {
        userRepository.save(new User(
                "User One",
                "u1@test.com",
                "9000000002",
                LocalDate.of(1999, 1, 1)
        ));

        userRepository.save(new User(
                "User Two",
                "u2@test.com",
                "9000000003",
                LocalDate.of(1998, 1, 1)
        ));

        long count = userRepository.count();

        assertThat(count).isEqualTo(2);
    }
}
