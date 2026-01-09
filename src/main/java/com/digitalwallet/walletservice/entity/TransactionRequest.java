package com.digitalwallet.walletservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "transaction_requests",
        uniqueConstraints = @UniqueConstraint(columnNames = "requestId")
)
public class TransactionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requestId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected TransactionRequest() {}

    public TransactionRequest(String requestId) {
        this.requestId = requestId;
        this.createdAt = LocalDateTime.now();
    }
}
