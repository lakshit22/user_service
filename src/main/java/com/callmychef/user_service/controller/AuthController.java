package com.callmychef.user_service.controller;

import com.callmychef.user_service.dto.request.LoginRequestDTO;
import com.callmychef.user_service.dto.request.SignupRequestDto;
import com.callmychef.user_service.dto.response.LoginResponseDTO;
import com.callmychef.user_service.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto dto) {
        authService.signUp(dto);
        return ResponseEntity.ok("User registered successfully");
    }
}
