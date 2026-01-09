package com.digitalwallet.walletservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, length = 10)
    private String currency = "INR";

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // REQUIRED for optimistic locking
    @Version
    @Column(nullable = false)
    private Long version;

    // One wallet → many sent transactions
    @OneToMany(mappedBy = "senderWallet", cascade = CascadeType.ALL)
    private List<Transaction> sentTransactions = new ArrayList<>();

    // One wallet → many received transactions
    @OneToMany(mappedBy = "receiverWallet", cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions = new ArrayList<>();

    protected Wallet() {
    }

    public Wallet(User user, BigDecimal balance) {
        this.user = user;
        this.balance = balance;
    }

    public Wallet(User user, BigDecimal balance, String currency) {
        this.user = user;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters

    public Long getWalletId() {
        return walletId;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getVersion() {
        return version;
    }

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void debit(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

}
