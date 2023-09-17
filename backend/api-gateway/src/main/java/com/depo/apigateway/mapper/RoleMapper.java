package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.request.role.RoleCreateRequest;
import com.depo.apigateway.dto.request.role.RoleUpdateRequest;
import com.depo.apigateway.dto.response.role.RoleCreateResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
import com.depo.apigateway.dto.response.role.RoleGetByIdResponse;
import com.depo.apigateway.dto.response.role.RoleUpdateResponse;
import com.depo.apigateway.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    List<RoleGetAllResponse> roleToGetAllResponse(List<Role> roles);

    RoleGetByIdResponse roleToGetByIdResponse(Role role);

    RoleCreateResponse roleToCreateResponse(Role role);

    RoleUpdateResponse roleToUpdateResponse(Role role);

    Role roleCreateRequestToRole(RoleCreateRequest request);

    Role roleUpdateRequestToRole(RoleUpdateRequest request);

}
