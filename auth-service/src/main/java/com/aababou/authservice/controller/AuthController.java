package com.aababou.authservice.controller;

import com.aababou.authservice.dto.LoginRequestDto;
import com.aababou.authservice.dto.LoginResponseDto;
import com.aababou.authservice.model.User;
import com.aababou.authservice.service.AuthService;
import com.aababou.authservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
author otman
    */
@RestController()
@RequestMapping()
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final UserService userService;
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

   // @Operation(summary = "Generate Token on user Login")
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
//        Optional<String> tokenOptional = authService.authenticate(loginRequestDto);
//        if(tokenOptional.isEmpty()){
//            log.info("Login failed");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String token = tokenOptional.get();
//        return ResponseEntity.ok(new LoginResponseDto(token));
//    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        System.out.println("🔥 LOGIN ENDPOINT HIT");
        Optional<String> tokenOptional =authService.authenticate(loginRequestDto);
        if(tokenOptional.isEmpty()) {
            log.info("Login failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = tokenOptional.get();
        log.info("Login successful");
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @Operation(summary = "Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        //Authorization: Bearer <token>
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return authService.validateToken(authHeader.substring(7))
                ?ResponseEntity.ok().build()
                :ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}
