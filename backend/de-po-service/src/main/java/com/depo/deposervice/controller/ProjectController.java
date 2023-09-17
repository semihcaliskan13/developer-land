package com.depo.deposervice.controller;

import com.depo.deposervice.dto.request.project.ProjectCreateRequest;
import com.depo.deposervice.dto.request.project.ProjectUpdateRequest;
import com.depo.deposervice.dto.response.codebase.CodebaseByProjectIdResponse;
import com.depo.deposervice.dto.response.project.ProjectCreateResponse;
import com.depo.deposervice.dto.response.project.ProjectGetAllResponse;
import com.depo.deposervice.dto.response.project.ProjectGetByIdResponse;
import com.depo.deposervice.dto.response.project.ProjectUpdateResponse;
import com.depo.deposervice.entity.Codebase;
import com.depo.deposervice.entity.Project;
import com.depo.deposervice.mapper.CodebaseMapper;
import com.depo.deposervice.mapper.ProjectMapper;
import com.depo.deposervice.service.CodebaseService;
import com.depo.deposervice.service.ProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final CodebaseService codebaseService;

    public ProjectController(ProjectService projectService, CodebaseService codebaseService) {
        this.projectService = projectService;
        this.codebaseService = codebaseService;
    }

    @GetMapping("/{id}")
    public ProjectGetByIdResponse getProjectById(@PathVariable String id){
        Project project = projectService.getProjectById(id);
        ProjectGetByIdResponse response = ProjectMapper.INSTANCE.mapProjectToGetByIdProject(project);
        return response;
    }

    @GetMapping
    public List<ProjectGetAllResponse> getAllProjects(){
        List<Project> projects =projectService.getAllProjects();
        List<ProjectGetAllResponse> responses =ProjectMapper.INSTANCE.mapProjectsToGetAllProjects(projects);
        return responses;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectCreateResponse createProject(@RequestBody ProjectCreateRequest projectCreateRequest) {
        Project project = ProjectMapper.INSTANCE.mapCreateRequestToProject(projectCreateRequest);
        project=projectService.saveProject(project);
        ProjectCreateResponse response = ProjectMapper.INSTANCE.mapProjectToCreateResponse(project);
        return response;
    }

    @PutMapping
    public ProjectUpdateResponse updateProject(@RequestBody ProjectUpdateRequest projectUpdateRequest){
        Project project =ProjectMapper.INSTANCE.mapUpdateRequestToProject(projectUpdateRequest);
        project =projectService.updateProject(project);
        ProjectUpdateResponse response = ProjectMapper.INSTANCE.mapProjectToUpdateResponse(project);
        return response;
    }

    @GetMapping("/{id}/codebases")
    public List<CodebaseByProjectIdResponse> getCodeBasesByProjectId(@PathVariable String id){
        List<Codebase> codebases = codebaseService.getCodeBasesByProjectId(id);
        return CodebaseMapper.INSTANCE.codebaseToByProjectIdResponse(codebases);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id){
        projectService.deleteProject(id);
    }
}
