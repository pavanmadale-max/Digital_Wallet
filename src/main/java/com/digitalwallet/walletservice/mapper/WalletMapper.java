package com.digitalwallet.walletservice.mapper;


import com.digitalwallet.walletservice.dto.WalletResponseDTO;
import com.digitalwallet.walletservice.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Mapping(source = "user.userId", target = "userId")
    WalletResponseDTO toResponse(Wallet wallet);
}



















//public class WalletMapper {
//
//    public static WalletResponseDTO toResponse(Wallet wallet) {
//        WalletResponseDTO dto = new WalletResponseDTO();
//
//        // READ from wallet entity
//        dto.setWalletId(wallet.getWalletId());
//        dto.setUserId(wallet.getUser().getUserId());
//        dto.setBalance(wallet.getBalance());
//        dto.setCurrency(wallet.getCurrency());
//
//        return dto;
//    }
//}
