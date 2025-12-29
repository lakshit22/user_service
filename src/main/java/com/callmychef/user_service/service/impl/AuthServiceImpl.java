package com.callmychef.user_service.service.impl;

import com.callmychef.user_service.dto.request.LoginRequestDTO;
import com.callmychef.user_service.dto.request.SignupRequestDto;
import com.callmychef.user_service.dto.response.LoginResponseDTO;
import com.callmychef.user_service.enums.Role;
import com.callmychef.user_service.enums.Status;
import com.callmychef.user_service.exception.InvalidCredentialsException;
import com.callmychef.user_service.exception.UserAlreadyExistsException;
import com.callmychef.user_service.userdb.entity.User;
import com.callmychef.user_service.userdb.repository.UserRepository;
import com.callmychef.user_service.service.AuthService;
import com.callmychef.user_service.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LogManager.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow( () -> new InvalidCredentialsException("Invalid Credentials"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Password didn't Match");
        }

        if(user.getStatus() != Status.ACTIVE){
            throw new InvalidCredentialsException("User is not Active");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(token,user.getEmail(),user.getRole().toString());
    }

    @Override
    public void signUp(SignupRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setPhoneNo(Long.parseLong(dto.getPhone()));
        user.setRole(Role.USER);
        user.setFirstName(dto.getFirstName());
        if(dto.getLastName() != null){
            user.setLastName(dto.getLastName());
        }
        try{
            userRepository.save(user);
        }catch (Exception e){
            if(e.getCause() instanceof ConstraintViolationException cve && cve.getSQLException().getErrorCode() == 1062){
                throw new UserAlreadyExistsException("User Details Already Exists");
            }
            log.error(String.valueOf(e.getCause()));
        }
    }
}
