package com.depo.apigateway.service;


import com.depo.apigateway.dto.CodebaseDto;
import com.depo.apigateway.dto.CodebaseRequirementLinkDto;
import com.depo.apigateway.dto.RequirementAccountDto;
import com.depo.apigateway.dto.request.ContainerizationDocument.ContainerizationDocumentRequirementCreateRequest;
import com.depo.apigateway.dto.request.ContainerizationDocument.ContainerizationDocumentRequirementUpdateRequest;
import com.depo.apigateway.dto.request.RequirementScript.RequirementScriptCreateRequest;
import com.depo.apigateway.dto.request.RequirementScript.RequirementScriptExecuteRequest;
import com.depo.apigateway.dto.response.ContainerizationDocument.ContainerizationDocumentCombinedFileResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountCreateResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountGetByIdResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountUpdateResponse;
import com.depo.apigateway.dto.response.RequirementScript.RequirementScriptExecuteResponse;
import com.depo.apigateway.dto.response.RequirementScript.RequirementScriptGetByIdResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseCreateResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseGetByIdResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseUpdateResponse;
import com.depo.apigateway.dto.response.requirement.RequirementGetAllResponse;
import com.depo.apigateway.util.CustomFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Nullable;
import java.util.List;

@FeignClient(name = "de-po-service", path = "/api/v1/codebases", configuration = CustomFeignClient.class)
public interface CodebaseService {

    @GetMapping
    List<CodebaseGetAllResponse> getAllCodebases();

    @GetMapping("{id}")
    CodebaseGetByIdResponse getCodebaseById(@PathVariable String id);

    @GetMapping("{id}/requirements")
    List<RequirementGetAllResponse> getRequirementsByCodebaseId(@PathVariable String id);

    @PostMapping
    CodebaseCreateResponse createCodebase(@RequestBody CodebaseDto request);

    @PutMapping
    CodebaseUpdateResponse updateCodebase(@RequestBody CodebaseDto request);

    @PutMapping("{id}/requirements")
    void linkCodebaseWithRequirements(@PathVariable String id, @RequestBody List<CodebaseRequirementLinkDto> Ids);

    @DeleteMapping("{id}")
    void deleteCodebase(@PathVariable String id);

    @DeleteMapping("{id}/requirements/{requirementId}")
    void deleteRequirementsFromCodebase(@PathVariable String id, @PathVariable String requirementId);

    @PostMapping("{id}/requirements/{requirementId}/containerization-documents")
    void saveContainerizationDocumentToRequirement(@PathVariable String id, @PathVariable String requirementId, @RequestBody  ContainerizationDocumentRequirementCreateRequest request);

    @PutMapping("{id}/requirements/{requirementId}/containerization-documents")
    void updateContainerizationDocumentRequirement(@PathVariable String id, @PathVariable String requirementId,
                                                   @RequestBody ContainerizationDocumentRequirementUpdateRequest file);

    @DeleteMapping("{id}/requirements/{requirementId}/containerization-documents")
    void deleteContainerizationDocumentFromRequirement(@PathVariable String id, @PathVariable String requirementId);

    @GetMapping("{id}/requirements/containerization-documents")
    ResponseEntity<ContainerizationDocumentCombinedFileResponse> getContainerizationDocumentByRequirements(@PathVariable String id, @Nullable @RequestParam("requirementId") List<String> requirementIds);

    @PostMapping("{id}/requirements/{requirementId}/accounts")
    RequirementAccountCreateResponse createRequirementAccount(@PathVariable String id, @PathVariable String requirementId,
                                                              @RequestBody RequirementAccountDto request);

    @GetMapping("{id}/requirements/{requirementId}/accounts/{requirementAccountId}")
    RequirementAccountGetByIdResponse getRequirementAccountById(@PathVariable String id, @PathVariable String requirementId,
                                                          @PathVariable String requirementAccountId);

    @PutMapping("{id}/requirements/{requirementId}/accounts")
    RequirementAccountUpdateResponse updateRequirementAccount(@PathVariable String id, @PathVariable String requirementId,
                                                              @RequestBody RequirementAccountDto request);

    @DeleteMapping("{id}/requirements/{requirementId}/accounts/{requirementAccountId}")
    void deleteRequirementAccountById(@PathVariable String id, @PathVariable String requirementId, @PathVariable String requirementAccountId);

    @PostMapping("{id}/requirements/{requirementId}/scripts")
    void saveRequirementScript(@PathVariable String id, @PathVariable String requirementId, @RequestBody RequirementScriptCreateRequest request);

    @GetMapping("{id}/requirements/{requirementId}/scripts/{scriptId}")
    RequirementScriptGetByIdResponse getRequirementScriptById(@PathVariable String id, @PathVariable String requirementId, @PathVariable String scriptId);

    @PostMapping("{id}/requirements/{requirementId}/scripts/execute/{scriptId}")
    RequirementScriptExecuteResponse executeRequirementScript(@PathVariable String id, @PathVariable String requirementId,
                                                   @PathVariable  String scriptId, @RequestBody RequirementScriptExecuteRequest request);

    @DeleteMapping("{id}/requirements/{requirementId}/scripts/{scriptId}")
    void deleteRequirementScriptById(@PathVariable String scriptId);
}
