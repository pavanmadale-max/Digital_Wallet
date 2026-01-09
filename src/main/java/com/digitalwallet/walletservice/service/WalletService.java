package com.digitalwallet.walletservice.service;

import com.digitalwallet.walletservice.dto.WalletRequestDTO;
import com.digitalwallet.walletservice.dto.WalletResponseDTO;

import java.math.BigDecimal;

public interface WalletService {

    WalletResponseDTO createWallet(WalletRequestDTO request);
    WalletResponseDTO getWallet(Long walletId);
    BigDecimal getBalance(Long walletId);
    public String getServiceStatus();
}
