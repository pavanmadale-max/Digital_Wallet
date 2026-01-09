package com.digitalwallet.walletservice.controller;

import com.digitalwallet.walletservice.dto.UserRequestDTO;
import com.digitalwallet.walletservice.dto.UserResponseDTO;
import com.digitalwallet.walletservice.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO request) {
        log.info("Creating user with email={}, fullName={}", request.getEmail(), request.getFullName());
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        log.info("Fetching user with id={}", id);
        return userService.getUserById(id);
    }
}
