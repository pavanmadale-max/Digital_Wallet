package com.digitalwallet.walletservice.service;

import org.springframework.stereotype.Service;

@Service
public class WalletService {

    public String getServiceStatus(){
        return "Wallet service is working properly";
    }
}
