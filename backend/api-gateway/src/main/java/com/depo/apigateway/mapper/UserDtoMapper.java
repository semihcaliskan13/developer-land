package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.UserDto;
import com.depo.apigateway.dto.request.user.UserCreateRequest;
import com.depo.apigateway.dto.request.user.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDto userCreateRequestToUser(UserCreateRequest request);

    UserDto userUpdateRequestToUser(UserUpdateRequest request);
}
