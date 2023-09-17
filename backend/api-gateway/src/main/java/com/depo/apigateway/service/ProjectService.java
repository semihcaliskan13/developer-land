package com.depo.apigateway.service;

import com.depo.apigateway.dto.ProjectDto;
import com.depo.apigateway.dto.response.codebase.CodebaseByProjectIdResponse;
import com.depo.apigateway.dto.response.project.ProjectCreateResponse;
import com.depo.apigateway.dto.response.project.ProjectGetAllResponse;
import com.depo.apigateway.dto.response.project.ProjectGetByIdResponse;
import com.depo.apigateway.dto.response.project.ProjectUpdateResponse;
import com.depo.apigateway.util.CustomFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "de-po-service", path = "/api/v1/projects", configuration = CustomFeignClient.class)
public interface ProjectService {
    @GetMapping
    List<ProjectGetAllResponse> getProjects();

    @GetMapping("{id}")
    ProjectGetByIdResponse getProjectById(@PathVariable String id);

    @PostMapping
    ProjectCreateResponse createProject(@RequestBody ProjectDto request);

    @PutMapping
    ProjectUpdateResponse updateProject(@RequestBody ProjectDto request);

    @DeleteMapping("{id}")
    void deleteProject(@PathVariable String id);

    @GetMapping("{id}/codebases")
    CodebaseByProjectIdResponse getCodebaseByProjectId(@PathVariable String id);

}
