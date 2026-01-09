package com.digitalwallet.walletservice.service;

import com.digitalwallet.walletservice.dto.UserRequestDTO;
import com.digitalwallet.walletservice.dto.UserResponseDTO;
import com.digitalwallet.walletservice.entity.User;
import com.digitalwallet.walletservice.exception.ResourceNotFoundException;
import com.digitalwallet.walletservice.mapper.UserMapper;
import com.digitalwallet.walletservice.repository.UserRepository;
import com.digitalwallet.walletservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }
}
