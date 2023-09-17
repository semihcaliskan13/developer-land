package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.request.role.UserRoleUpdateRequest;
import com.depo.apigateway.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    List<UserRole> userRoleUpdateRequestToRole(List<UserRoleUpdateRequest> request);

}
