package com.depo.deposervice.controller;

import com.depo.deposervice.dto.request.requirement.RequirementCreateRequest;
import com.depo.deposervice.dto.request.requirement.RequirementUpdateRequest;
import com.depo.deposervice.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.deposervice.dto.response.requirement.RequirementCreateResponse;
import com.depo.deposervice.dto.response.requirement.RequirementGetAllResponse;
import com.depo.deposervice.dto.response.requirement.RequirementGetByIdResponse;
import com.depo.deposervice.dto.response.requirement.RequirementUpdateResponse;
import com.depo.deposervice.entity.CodebaseRequirement;
import com.depo.deposervice.entity.Requirement;
import com.depo.deposervice.mapper.CodebaseMapper;
import com.depo.deposervice.mapper.RequirementMapper;
import com.depo.deposervice.service.CodebaseRequirementService;
import com.depo.deposervice.service.CodebaseService;
import com.depo.deposervice.service.ContainerizationDocumentService;
import com.depo.deposervice.service.RequirementService;
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
@RequestMapping("/api/v1/requirements")
public class RequirementController {
    private final RequirementService requirementService;
    private final CodebaseRequirementService codebaseRequirementService;
    private final CodebaseService codebaseService;
    private final ContainerizationDocumentService containerizationDocumentService;

    public RequirementController(@Qualifier("requirementServiceImpl") RequirementService requirementService, CodebaseRequirementService codebaseRequirementService,  CodebaseService codebaseService, @Qualifier("containerizationDocumentServiceImpl") ContainerizationDocumentService containerizationDocumentService) {
        this.requirementService = requirementService;
        this.codebaseRequirementService = codebaseRequirementService;
        this.codebaseService = codebaseService;
        this.containerizationDocumentService = containerizationDocumentService;
    }

    @GetMapping
    public List<RequirementGetAllResponse> getAll() {
        return RequirementMapper.INSTANCE.requirementToGetAllRequirements(requirementService.getAll());
    }

    @GetMapping("{id}")
    public RequirementGetByIdResponse getById(@PathVariable String id) {
        return RequirementMapper.INSTANCE.requirementToGetByIdRequirement(requirementService.getById(id));
    }

    @GetMapping("{id}/codebases")
    public List<CodebaseGetAllResponse> getCodebasesByRequirementId(@PathVariable String id) {
        List<CodebaseRequirement> codebaseRequirements = codebaseRequirementService.getAllByRequirementId(id);
        List<String> codebaseIds = codebaseRequirements.stream().map(CodebaseRequirement::getCodebaseId).toList();
        return CodebaseMapper.INSTANCE.codebaseToGetAllCodeBasesResponse(codebaseService.getAllCodebasesByIds(codebaseIds));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequirementCreateResponse save(@RequestBody RequirementCreateRequest request) {
        Requirement requirement = RequirementMapper.INSTANCE.createRequirementRequestToRequirement(request);
        requirement = requirementService.save(requirement);
        return RequirementMapper.INSTANCE.requirementToCreateResponse(requirement);
    }

    @PutMapping
    public RequirementUpdateResponse update(@RequestBody RequirementUpdateRequest request) {
        Requirement requirement = RequirementMapper.INSTANCE.updateRequirementRequestToRequirement(request);
        requirement = requirementService.update(requirement);
        return RequirementMapper.INSTANCE.requirementToUpdateResponse(requirement);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        Requirement requirement = requirementService.getById(id);
        requirementService.delete(id);
        codebaseRequirementService.deleteAllByRequirementId(id);
        containerizationDocumentService.deleteByRequirement(requirement.getName(),requirement.getVersion());
    }

}
