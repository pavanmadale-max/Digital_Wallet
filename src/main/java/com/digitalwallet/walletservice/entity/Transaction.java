package com.digitalwallet.walletservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_wallet_id")
    private Wallet senderWallet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_wallet_id")
    private Wallet receiverWallet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private BigDecimal amount;

    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime transactionDate;

    protected Transaction() {
    }

    public Transaction(Wallet senderWallet, Wallet receiverWallet,
                       TransactionType transactionType, BigDecimal amount, String description) {
        this.senderWallet = senderWallet;
        this.receiverWallet = receiverWallet;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    public static Transaction credit(Wallet wallet, BigDecimal amount) {
        return new Transaction(
                null,
                wallet,
                TransactionType.CREDIT,
                amount,
                "Wallet credit"
        );
    }

    public static Transaction debit(Wallet wallet, BigDecimal amount) {
        return new Transaction(
                wallet,
                null,
                TransactionType.DEBIT,
                amount,
                "Wallet debit"
        );
    }

    public static Transaction transfer(Wallet sender, Wallet receiver, BigDecimal amount) {
        return new Transaction(
                sender,
                receiver,
                TransactionType.TRANSFER,
                amount,
                "Wallet transfer"
        );
    }







    @PrePersist
    protected void onCreate() {
        this.transactionDate = LocalDateTime.now();
    }

    // getters only (immutable transaction)

    public Long getTransactionId() {
        return transactionId;
    }

    public Wallet getSenderWallet() {
        return senderWallet;
    }

    public Wallet getReceiverWallet() {
        return receiverWallet;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}

