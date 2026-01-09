package com.digitalwallet.walletservice.controller;

import com.digitalwallet.walletservice.dto.WalletRequestDTO;
import com.digitalwallet.walletservice.dto.WalletResponseDTO;
import com.digitalwallet.walletservice.service.WalletService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private static final Logger log = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WalletResponseDTO createWallet(@Valid @RequestBody WalletRequestDTO request) {
        log.info("Creating wallet for userId={}, initialBalance={}", request.getUserId(), request.getBalance());
        return walletService.createWallet(request);
    }

    @GetMapping("/{id}")
    public WalletResponseDTO getWallet(@PathVariable Long id) {
        log.info("Fetching wallet with id={}", id);
        return walletService.getWallet(id);
    }

    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id) {
        log.info("Fetching balance for walletId={}", id);
        return walletService.getBalance(id);
    }
}
