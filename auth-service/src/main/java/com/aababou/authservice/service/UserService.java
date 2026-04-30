package com.aababou.authservice.service;

import com.aababou.authservice.model.User;
import com.aababou.authservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
author otman
    */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
