package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.request.codebase.UserCodebaseUpdateRequest;
import com.depo.apigateway.entity.UserCodebase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserCodebaseMapper {
    UserCodebaseMapper INSTANCE = Mappers.getMapper(UserCodebaseMapper.class);

    List<UserCodebase> updateRequestToUserCodebases(List<UserCodebaseUpdateRequest> requests);
}
