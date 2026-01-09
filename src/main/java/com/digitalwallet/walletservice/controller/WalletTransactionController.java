package com.digitalwallet.walletservice.controller;

import com.digitalwallet.walletservice.dto.TransactionRequestDTO;
import com.digitalwallet.walletservice.dto.TransferRequestDTO;
import com.digitalwallet.walletservice.dto.WalletAmountRequestDTO;
import com.digitalwallet.walletservice.service.WalletTransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class WalletTransactionController {

     WalletTransactionService service;

    @PostMapping("/credit")
    public void credit(@RequestBody @Valid WalletAmountRequestDTO dto) {
        service.credit(dto.walletId(), dto.amount(), dto.requestId());
    }

    @PostMapping("/debit")
    public void debit(@RequestBody @Valid WalletAmountRequestDTO dto) {
        service.debit(dto.walletId(), dto.amount(), dto.requestId());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody @Valid TransferRequestDTO dto) {
        service.transfer(
                dto.fromWalletId(),
                dto.toWalletId(),
                dto.amount(),
                dto.requestId()
        );
    }

}
