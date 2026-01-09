package com.digitalwallet.walletservice.repository;

import com.digitalwallet.walletservice.entity.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

final class ReflectionUtil {

    private ReflectionUtil() {}

    // ---------- generic field setter ----------
    private static void set(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- User ----------
    static User createUser(String email, String phone) {
        try {
            Constructor<User> c = User.class.getDeclaredConstructor();
            c.setAccessible(true);
            User user = c.newInstance();

            set(user, "email", email);
            set(user, "phoneNumber", phone);
            set(user, "fullName", "Test User");
            set(user, "status", UserStatus.ACTIVE);
            set(user, "createdAt", LocalDateTime.now());
            set(user, "dateOfBirth", LocalDate.now().minusYears(25));

            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- Wallet ----------
    static Wallet createWallet(User user) {
        try {
            Constructor<Wallet> c = Wallet.class.getDeclaredConstructor();
            c.setAccessible(true);
            Wallet wallet = c.newInstance();

            set(wallet, "user", user);
            set(wallet, "balance", BigDecimal.valueOf(1000));
            set(wallet, "currency", "INR");
            set(wallet, "createdAt", LocalDateTime.now());

            return wallet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- Transaction ----------
    static Transaction createTransaction(
            Wallet sender,
            Wallet receiver,
            BigDecimal amount
    ) {
        try {
            Constructor<Transaction> c = Transaction.class.getDeclaredConstructor();
            c.setAccessible(true);
            Transaction tx = c.newInstance();

            set(tx, "senderWallet", sender);
            set(tx, "receiverWallet", receiver);
            set(tx, "amount", amount);
            set(tx, "transactionType", TransactionType.TRANSFER);
            set(tx, "transactionDate", LocalDateTime.now());
            set(tx, "description", "test");

            return tx;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
