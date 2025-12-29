package com.callmychef.user_service.service;

import com.callmychef.user_service.userdb.entity.User;

public interface JwtService {
    public String generateToken(User user);
    public String extractEmail(String token);
}
