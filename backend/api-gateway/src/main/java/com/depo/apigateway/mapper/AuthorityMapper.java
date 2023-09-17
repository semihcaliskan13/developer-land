package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.request.authority.AuthorityCreateRequest;
import com.depo.apigateway.dto.request.authority.AuthorityUpdateRequest;
import com.depo.apigateway.dto.response.authority.AuthorityCreateResponse;
import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.authority.AuthorityGetByIdResponse;
import com.depo.apigateway.dto.response.authority.AuthorityUpdateResponse;
import com.depo.apigateway.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    List<AuthorityGetAllResponse> authorityToGetAllResponse(List<Authority> authorities);

    AuthorityGetByIdResponse authorityToGetByIdResponse(Authority authority);

    Authority authorityCreateRequestToAuthority(AuthorityCreateRequest request);

    Authority authorityUpdateRequestToAuthority(AuthorityUpdateRequest request);

    AuthorityCreateResponse authorityToCreateResponse(Authority authority);

    AuthorityUpdateResponse authorityToUpdateResponse(Authority authority);
}
