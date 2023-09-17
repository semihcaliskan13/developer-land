package com.depo.apigateway.service;

import com.depo.apigateway.dto.CustomUserDetails;
import com.depo.apigateway.dto.LoginDto;
import com.depo.apigateway.dto.RefreshTokenLoginDto;
import com.depo.apigateway.dto.UserCredential;
import com.depo.apigateway.dto.response.auth.LoginResponse;
import com.depo.apigateway.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthenticationService(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    public LoginResponse login(LoginDto loginRequest) {
        String username = loginRequest.getUsername();
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return generateToken(username);
    }

    public LoginResponse refreshLogin(RefreshTokenLoginDto request) {
        jwtUtil.tokenControl(request.getRefreshToken());
        String username = jwtUtil.findUsername(request.getRefreshToken());
        return generateToken(username);
    }

    private LoginResponse generateToken(String username) {
        UserCredential user = userService.getUserCredentialsByUsername(username);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String token = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        return LoginResponse.builder().accessToken(token).refreshToken(refreshToken).build();
    }
}
