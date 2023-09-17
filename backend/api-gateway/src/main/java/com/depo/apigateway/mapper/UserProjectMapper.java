package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.request.project.UserProjectUpdateRequest;
import com.depo.apigateway.entity.UserProject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserProjectMapper {
    UserProjectMapper INSTANCE = Mappers.getMapper(UserProjectMapper.class);

    List<UserProject> updateRequestToUserProjects(List<UserProjectUpdateRequest> requests);

}
