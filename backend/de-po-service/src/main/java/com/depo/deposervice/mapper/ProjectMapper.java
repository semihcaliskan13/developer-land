package com.depo.deposervice.mapper;

import com.depo.deposervice.dto.request.project.ProjectCreateRequest;
import com.depo.deposervice.dto.request.project.ProjectUpdateRequest;
import com.depo.deposervice.dto.response.project.ProjectCreateResponse;
import com.depo.deposervice.dto.response.project.ProjectGetAllResponse;
import com.depo.deposervice.dto.response.project.ProjectGetByIdResponse;
import com.depo.deposervice.dto.response.project.ProjectUpdateResponse;
import com.depo.deposervice.entity.Project;
import com.depo.deposervice.entry.ProjectEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper( ProjectMapper.class );
    Project mapCreateRequestToProject(ProjectCreateRequest project);
    List<ProjectGetAllResponse> mapProjectsToGetAllProjects(List<Project> projects);
    ProjectGetByIdResponse mapProjectToGetByIdProject(Project project);
    Project mapUpdateRequestToProject(ProjectUpdateRequest project);
    ProjectCreateResponse mapProjectToCreateResponse(Project project);
    ProjectUpdateResponse mapProjectToUpdateResponse(Project project);
    Project projectEntryToProject(ProjectEntry projectEntry);
    List<Project> projectEntriesToProjects(List<ProjectEntry> projectEntries);
}
