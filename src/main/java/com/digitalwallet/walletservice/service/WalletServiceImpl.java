package com.digitalwallet.walletservice.service;

import com.digitalwallet.walletservice.dto.WalletRequestDTO;
import com.digitalwallet.walletservice.dto.WalletResponseDTO;
import com.digitalwallet.walletservice.entity.User;
import com.digitalwallet.walletservice.entity.Wallet;
import com.digitalwallet.walletservice.exception.ResourceNotFoundException;
import com.digitalwallet.walletservice.mapper.WalletMapper;
import com.digitalwallet.walletservice.repository.UserRepository;
import com.digitalwallet.walletservice.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(WalletRepository walletRepository,
                             UserRepository userRepository,
                             WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public WalletResponseDTO createWallet(WalletRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Wallet wallet = new Wallet(user, request.getBalance(), "INR");
        return walletMapper.toResponse(walletRepository.save(wallet));
    }

    @Override
    public WalletResponseDTO getWallet(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        return walletMapper.toResponse(wallet);
    }

    @Override
    public BigDecimal getBalance(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }

    @Override
    public String getServiceStatus() {
        return "Wallet Service is working properly";
    }
}
