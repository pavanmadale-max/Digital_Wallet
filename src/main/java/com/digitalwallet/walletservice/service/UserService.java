package com.digitalwallet.walletservice.service;

import com.digitalwallet.walletservice.dto.UserRequestDTO;
import com.digitalwallet.walletservice.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);
    UserResponseDTO getUserById(Long id);
}
