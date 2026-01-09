package com.digitalwallet.walletservice.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


    // One user → many wallets
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wallet> wallets = new ArrayList<>();

    protected User() {
        // JPA only
    }

    public User(String fullName, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.status = UserStatus.ACTIVE;
    }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters (NO setter for userId)

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

}
