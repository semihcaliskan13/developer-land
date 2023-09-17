package com.depo.apigateway.controller;


import com.depo.apigateway.dto.LoginDto;
import com.depo.apigateway.dto.RefreshTokenLoginDto;
import com.depo.apigateway.dto.request.auth.LoginRequest;
import com.depo.apigateway.dto.request.auth.RefreshTokenLoginRequest;
import com.depo.apigateway.dto.response.auth.LoginResponse;
import com.depo.apigateway.mapper.AuthMapper;
import com.depo.apigateway.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        LoginDto loginDto = AuthMapper.INSTANCE.loginRequestToLoginDto(loginRequest);
        return authenticationService.login(loginDto);
    }

    @PostMapping("/refresh")
    public LoginResponse refreshTokenLogin(@RequestBody RefreshTokenLoginRequest request) {
        RefreshTokenLoginDto refreshTokenLoginDto = AuthMapper.INSTANCE.refreshTokenLoginToRefreshTokenLoginDto(request);
        return authenticationService.refreshLogin(refreshTokenLoginDto);
    }
}

