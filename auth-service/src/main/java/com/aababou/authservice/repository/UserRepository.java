package com.aababou.authservice.repository;

import com.aababou.authservice.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/*
author otman
    */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(@NotBlank(message = "Message is required") @Email(message = "Email should be a valid Email Address ") String email);
}
