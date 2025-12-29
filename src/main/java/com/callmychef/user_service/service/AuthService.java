package com.callmychef.user_service.service;

import com.callmychef.user_service.dto.request.LoginRequestDTO;
import com.callmychef.user_service.dto.request.SignupRequestDto;
import com.callmychef.user_service.dto.response.LoginResponseDTO;

public interface AuthService {
    public LoginResponseDTO login(LoginRequestDTO dto);
    public void signUp(SignupRequestDto dto);
}
