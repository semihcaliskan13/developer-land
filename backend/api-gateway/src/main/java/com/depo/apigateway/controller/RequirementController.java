package com.depo.apigateway.controller;

import com.depo.apigateway.dto.RequirementDto;
import com.depo.apigateway.dto.request.requirement.RequirementCreateRequest;
import com.depo.apigateway.dto.request.requirement.RequirementUpdateRequest;
import com.depo.apigateway.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.apigateway.dto.response.requirement.RequirementCreateResponse;
import com.depo.apigateway.dto.response.requirement.RequirementGetAllResponse;
import com.depo.apigateway.dto.response.requirement.RequirementGetByIdResponse;
import com.depo.apigateway.dto.response.requirement.RequirementUpdateResponse;
import com.depo.apigateway.mapper.RequirementDtoMapper;
import com.depo.apigateway.service.RequirementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/requirements")
public class RequirementController {

    private final RequirementService requirementService;

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping
    public List<RequirementGetAllResponse> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    @GetMapping("{id}")
    public RequirementGetByIdResponse getRequirementById(@PathVariable String id) {
        return requirementService.getRequirementById(id);
    }

    @GetMapping("{id}/codebases")
    public List<CodebaseGetAllResponse> getCodebasesByRequirementId(@PathVariable String id){
        return requirementService.getCodebasesByRequirementId(id);
    }

    @PostMapping
    public RequirementCreateResponse createRequirement(@RequestBody RequirementCreateRequest request) {
        RequirementDto requirementDto = RequirementDtoMapper.INSTANCE.createRequirementRequestToRequirement(request);
        return requirementService.createRequirement(requirementDto);
    }

    @PutMapping
    public RequirementUpdateResponse updateRequirement(@RequestBody RequirementUpdateRequest request) {
        RequirementDto requirementDto = RequirementDtoMapper.INSTANCE.updateRequirementRequestToRequirement(request);
        return requirementService.updateRequirement(requirementDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirement(@PathVariable String id) {
        requirementService.deleteRequirement(id);

    }
}
