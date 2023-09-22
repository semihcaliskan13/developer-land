package com.depo.apigateway.controller;

import com.depo.apigateway.dto.ProjectDto;
import com.depo.apigateway.dto.request.project.ProjectCreateRequest;
import com.depo.apigateway.dto.request.project.ProjectUpdateRequest;
import com.depo.apigateway.dto.request.project.UserProjectUpdateRequest;
import com.depo.apigateway.dto.response.codebase.CodebaseByProjectIdResponse;
import com.depo.apigateway.dto.response.project.ProjectCreateResponse;
import com.depo.apigateway.dto.response.project.ProjectGetAllResponse;
import com.depo.apigateway.dto.response.project.ProjectGetByIdResponse;
import com.depo.apigateway.dto.response.project.ProjectUpdateResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import com.depo.apigateway.entity.UserProject;
import com.depo.apigateway.mapper.ProjectDtoMapper;
import com.depo.apigateway.mapper.UserProjectMapper;
import com.depo.apigateway.service.ProjectService;
import com.depo.apigateway.service.UserProjectService;
import com.depo.apigateway.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
@SecurityRequirement(name="bearerAuth")
public class ProjectController {

    private final ProjectService projectService;

    private final UserService userService;

    private final UserProjectService userProjectService;

    public ProjectController(ProjectService projectService, UserService userService, UserProjectService userProjectService) {
        this.projectService = projectService;
        this.userService = userService;
        this.userProjectService = userProjectService;
    }

    @GetMapping
    public List<ProjectGetAllResponse> getAllProjects() {
        List<ProjectGetAllResponse> projects = projectService.getProjects();
        List<String> projectIds = projects.stream()
                .map(ProjectGetAllResponse::getId)
                .collect(Collectors.toList());
        List<UserProject> userProjects = userProjectService.findAllByProjectId(projectIds);
        List<String> userIds = userProjects
                .stream().map(UserProject::getUserId).toList();
        List<UserGetAllResponse> users = userService.getUsersById(userIds);
        for (UserProject userProject : userProjects) {
            projects.stream().filter(project -> project.getId().equals(userProject.getProjectId()))
                    .forEach(project -> project.getUsers().addAll(users
                            .stream()
                            .filter(user -> user.getId().equals(userProject.getUserId())).toList()));
        }
        return projects;
    }

    @GetMapping("{id}")
    public ProjectGetByIdResponse getProjectById(@PathVariable String id) {
        ProjectGetByIdResponse project = projectService.getProjectById(id);
        List<String> userIds = userProjectService.findAllByProjectId(id)
                .stream().map(UserProject::getUserId).toList();
        project.setUsers(userService.getUsersById(userIds));
        return project;
    }

    @GetMapping("{id}/codebases")
    public CodebaseByProjectIdResponse getCodeBasesByProjectId(@PathVariable String id) {
        return projectService.getCodebaseByProjectId(id);
    }

    @PostMapping
    public ProjectCreateResponse createProject(@RequestBody ProjectCreateRequest request) {
        ProjectDto projectDto = ProjectDtoMapper.INSTANCE.mapCreateRequestToProject(request);
        ProjectCreateResponse response = projectService.createProject(projectDto);
        userProjectService.create(new UserProject(request.getUserId(), response.getId()));
        return response;
    }

    @PutMapping
    public ProjectUpdateResponse updateProject(@RequestBody ProjectUpdateRequest request) {
        ProjectDto projectDto = ProjectDtoMapper.INSTANCE.mapUpdateRequestToProject(request);
        return projectService.updateProject(projectDto);
    }

    @PutMapping("/users")
    public void linkUsersToProject(@RequestBody List<UserProjectUpdateRequest> request) {
        List<UserProject> userProjects = UserProjectMapper.INSTANCE.updateRequestToUserProjects(request);
        userProjectService.updateAll(userProjects);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        userProjectService.deleteAll(id);
    }

    @DeleteMapping("{id}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserProject(@PathVariable String id, @PathVariable String userId) {
        userProjectService.delete(id, userId);
    }
}
