package com.digitalwallet.walletservice.controller;

import com.digitalwallet.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    WalletService walletService;
    public HealthController(WalletService walletService){
        this.walletService=walletService;
    }

    @GetMapping("/health")
    public String health(){
       return walletService.getServiceStatus();
    }
}
