package com.digitalwallet.walletservice.mapper;

import com.digitalwallet.walletservice.dto.UserRequestDTO;
import com.digitalwallet.walletservice.dto.UserResponseDTO;
import com.digitalwallet.walletservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "wallets", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toResponse(User user);
}
