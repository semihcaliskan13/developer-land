package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.LoginDto;
import com.depo.apigateway.dto.RefreshTokenLoginDto;
import com.depo.apigateway.dto.request.auth.LoginRequest;
import com.depo.apigateway.dto.request.auth.RefreshTokenLoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    LoginDto loginRequestToLoginDto(LoginRequest request);

    RefreshTokenLoginDto refreshTokenLoginToRefreshTokenLoginDto(RefreshTokenLoginRequest request);
}
