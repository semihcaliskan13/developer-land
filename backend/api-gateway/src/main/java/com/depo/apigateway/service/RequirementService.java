package com.depo.apigateway.service;


import com.depo.apigateway.dto.RequirementDto;
import com.depo.apigateway.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.apigateway.dto.response.requirement.RequirementCreateResponse;
import com.depo.apigateway.dto.response.requirement.RequirementGetAllResponse;
import com.depo.apigateway.dto.response.requirement.RequirementGetByIdResponse;
import com.depo.apigateway.dto.response.requirement.RequirementUpdateResponse;
import com.depo.apigateway.util.CustomFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "de-po-service", path = "/api/v1/requirements", configuration = CustomFeignClient.class)
public interface RequirementService {

    @GetMapping
    List<RequirementGetAllResponse> getAllRequirements();

    @GetMapping("{id}")
    RequirementGetByIdResponse getRequirementById(@PathVariable String id);

    @GetMapping("{id}/codebases")
    List<CodebaseGetAllResponse> getCodebasesByRequirementId(@PathVariable String id);

    @PostMapping
    RequirementCreateResponse createRequirement(@RequestBody RequirementDto request);

    @PutMapping
    RequirementUpdateResponse updateRequirement(@RequestBody RequirementDto request);

    @DeleteMapping("{id}")
    void deleteRequirement(@PathVariable String id);
}
