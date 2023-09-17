package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.ProjectDto;
import com.depo.apigateway.dto.request.project.ProjectCreateRequest;
import com.depo.apigateway.dto.request.project.ProjectUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProjectDtoMapper {
    ProjectDtoMapper INSTANCE = Mappers.getMapper(ProjectDtoMapper.class);

    ProjectDto mapCreateRequestToProject(ProjectCreateRequest project);

    ProjectDto mapUpdateRequestToProject(ProjectUpdateRequest project);
}
