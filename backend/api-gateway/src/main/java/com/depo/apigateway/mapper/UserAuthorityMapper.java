package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.request.authority.UserAuthorityUpdateRequest;
import com.depo.apigateway.entity.UserAuthority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAuthorityMapper {
    UserAuthorityMapper INSTANCE = Mappers.getMapper(UserAuthorityMapper.class);

    List<UserAuthority> userAuthorityUpdateRequest(List<UserAuthorityUpdateRequest> request);
}
