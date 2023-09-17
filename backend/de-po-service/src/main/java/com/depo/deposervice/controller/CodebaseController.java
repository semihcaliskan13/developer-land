package com.depo.deposervice.controller;

import com.depo.deposervice.collection.ContainerizationDocument;
import com.depo.deposervice.collection.RequirementAccount;
import com.depo.deposervice.collection.RequirementScript;
import com.depo.deposervice.dto.RequirementScriptExecuteDto;
import com.depo.deposervice.dto.request.ContainerizationDocument.ContainerizationDocumentRequirementCreateRequest;
import com.depo.deposervice.dto.request.ContainerizationDocument.ContainerizationDocumentRequirementUpdateRequest;
import com.depo.deposervice.dto.request.RequirementAccount.RequirementAccountCreateRequest;
import com.depo.deposervice.dto.request.RequirementAccount.RequirementAccountUpdateRequest;
import com.depo.deposervice.dto.request.RequirementScript.RequirementScriptCreateRequest;
import com.depo.deposervice.dto.request.RequirementScript.RequirementScriptExecuteRequest;
import com.depo.deposervice.dto.request.codebase.CodebaseCreateRequest;
import com.depo.deposervice.dto.request.codebase.CodebaseRequirementLinkRequest;
import com.depo.deposervice.dto.request.codebase.CodebaseUpdateRequest;
import com.depo.deposervice.dto.response.ContainerizationDocument.ContainerizationDocumentCombinedFileResponse;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountCreateResponse;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountGetByIdResponse;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountUpdateResponse;
import com.depo.deposervice.dto.response.RequirementScript.RequirementScriptExecuteResponse;
import com.depo.deposervice.dto.response.RequirementScript.RequirementScriptGetByIdResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseCreateResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseGetByIdResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseUpdateResponse;
import com.depo.deposervice.dto.response.requirement.RequirementGetAllResponse;
import com.depo.deposervice.dto.response.requirement.RequirementGetByIdResponse;
import com.depo.deposervice.entity.Codebase;
import com.depo.deposervice.entity.CodebaseRequirement;
import com.depo.deposervice.entity.Requirement;
import com.depo.deposervice.mapper.CodebaseMapper;
import com.depo.deposervice.mapper.CodebaseRequirementMapper;
import com.depo.deposervice.mapper.RequirementAccountMapper;
import com.depo.deposervice.mapper.RequirementMapper;
import com.depo.deposervice.mapper.RequirementScriptMapper;
import com.depo.deposervice.service.CodebaseRequirementService;
import com.depo.deposervice.service.CodebaseService;
import com.depo.deposervice.service.ContainerizationDocumentService;
import com.depo.deposervice.service.RequirementAccountService;
import com.depo.deposervice.service.RequirementScriptService;
import com.depo.deposervice.service.RequirementService;
import com.depo.deposervice.util.ContainerizationDocumentUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/codebases")
public class CodebaseController {

    private final CodebaseService codeBaseService;
    private final CodebaseRequirementService codebaseRequirementService;
    private final RequirementService requirementService;
    private final ContainerizationDocumentService containerizationDocumentService;
    private final RequirementAccountService requirementAccountService;
    private final RequirementScriptService requirementScriptService;

    public CodebaseController(@Qualifier("codebaseServiceImpl") CodebaseService codeBaseService, CodebaseRequirementService codebaseRequirementService, @Qualifier("requirementServiceImpl") RequirementService requirementService, ContainerizationDocumentService containerizationDocumentService, RequirementAccountService requirementAccountService, RequirementScriptService requirementScriptService) {
        this.codeBaseService = codeBaseService;
        this.codebaseRequirementService = codebaseRequirementService;
        this.requirementService = requirementService;
        this.containerizationDocumentService = containerizationDocumentService;
        this.requirementAccountService = requirementAccountService;
        this.requirementScriptService = requirementScriptService;
    }

    @GetMapping
    public List<CodebaseGetAllResponse> getAllCodeBases() {
        List<Codebase> codebases = codeBaseService.getAllCodeBases();
        return CodebaseMapper.INSTANCE.codebaseToGetAllCodeBasesResponse(codebases);
    }

    @GetMapping("/{id}")
    public CodebaseGetByIdResponse getCodeBaseById(@PathVariable String id) {
        Codebase codebase = codeBaseService.getCodeBaseById(id);
        return CodebaseMapper.INSTANCE.codebaseToCodeBaseGetByIdResponse(codebase);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CodebaseCreateResponse createCodeBase(@RequestBody CodebaseCreateRequest codebaseCreateRequest) {
        Codebase codebase = CodebaseMapper.INSTANCE.createRequestToCodeBase(codebaseCreateRequest);
        codebase = codeBaseService.saveCodeBase(codebase);
        return CodebaseMapper.INSTANCE.codebaseToCreateResponse(codebase);
    }

    @PutMapping
    public CodebaseUpdateResponse updateCodeBase(@RequestBody CodebaseUpdateRequest codebaseUpdateRequest) {
        Codebase codebase = CodebaseMapper.INSTANCE.updateRequestToCodeBase(codebaseUpdateRequest);
        codebase = codeBaseService.updateCodeBase(codebase);
        return CodebaseMapper.INSTANCE.codebaseToUpdateResponse(codebase);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCodeBase(@PathVariable String id) {
        codeBaseService.deleteCodeBase(id);
        codebaseRequirementService.deleteAllByCodeBaseId(id);
    }

    @GetMapping("{id}/requirements")
    public List<RequirementGetAllResponse> getRequirementsByCodeBase(@PathVariable String id) {
        List<CodebaseRequirement> codebaseRequirement = codebaseRequirementService.getAllByCodebaseId(id);
        List<String> requirementIds = codebaseRequirement.stream().map(CodebaseRequirement::getRequirementId).toList();
        return RequirementMapper.INSTANCE.requirementToGetAllRequirements(requirementService.findAllById(requirementIds));
    }

    @PutMapping("{id}/requirements")
    public void linkCodebaseWithRequirements(@PathVariable String id, @RequestBody List<CodebaseRequirementLinkRequest> request) {
        List<CodebaseRequirement> codebaseRequirements = CodebaseRequirementMapper.INSTANCE.linkRequestToCodebaseRequirement(request);
        codebaseRequirementService.updateAll(codebaseRequirements);
    }

    @DeleteMapping("{id}/requirements/{requirementId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirementsFromCodebase(@PathVariable String id, @PathVariable String requirementId) {
        codebaseRequirementService.delete(id, requirementId);
    }

    @PostMapping("{id}/requirements/{requirementId}/containerization-documents")
    public void createContainerizationDocument(@PathVariable String id, @PathVariable String requirementId,
                                               @RequestBody ContainerizationDocumentRequirementCreateRequest file) throws IOException {
        RequirementGetByIdResponse requirement;
        ContainerizationDocument document;
        try {
            requirement = RequirementMapper.INSTANCE.requirementToGetByIdRequirement(requirementService.getById(requirementId));
            document = new ContainerizationDocument(null, file.getName(), requirement.getName(), requirement.getVersion(), file.getFile());
        } catch (Exception e) {
            document = new ContainerizationDocument(null, file.getName(), requirementId, null, file.getFile());
        }
        containerizationDocumentService.save(document);
    }

    @PutMapping("{id}/requirements/{requirementId}/containerization-documents")
    public void updateRequirementContainerizationDocument(@PathVariable String id, @PathVariable String requirementId,
                                                          @RequestBody ContainerizationDocumentRequirementUpdateRequest file) {

        Requirement requirement = requirementService.getById(requirementId);
        ContainerizationDocument documentOld = containerizationDocumentService.getByRequirementNameAndVersion(requirement.getName(), requirement.getVersion());
        ContainerizationDocument document = new ContainerizationDocument(documentOld.getId(), file.getName(), requirement.getName(), requirement.getVersion(), file.getFile());
        containerizationDocumentService.update(document);
    }

    @DeleteMapping("{id}/requirements/{requirementId}/containerization-documents")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContainerizationDocument(@PathVariable String id, @PathVariable String requirementId) {
        Requirement requirement = requirementService.getById(requirementId);
        containerizationDocumentService.deleteByRequirement(requirement.getName(), requirement.getVersion());
    }

    @GetMapping("{id}/requirements/containerization-documents")
    public ResponseEntity<ContainerizationDocumentCombinedFileResponse> getContainerizationDocument(@PathVariable String id, @Nullable @RequestParam("requirementId") List<String> requirementIds) {
        List<Requirement> requirements = requirementService.findAllById(requirementIds);
        List<String> requirementsWithVersions = requirements.stream()
                .map(r -> r.getName() + ":" + r.getVersion())
                .toList();
        List<String> requirementNames = requirements.stream().map(Requirement::getName).toList();
        List<ContainerizationDocument> documents = containerizationDocumentService.getDocuments(requirementsWithVersions, requirementNames);

        ByteArrayOutputStream outputStream = ContainerizationDocumentUtil.formatFilesAndZip(documents, requirements);
        ContainerizationDocumentCombinedFileResponse responseData = new ContainerizationDocumentCombinedFileResponse();
        String fileName = "combined-containerized-document.yml";
        responseData.setFileName(fileName);
        responseData.setFile(outputStream.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseData);
    }

    @PostMapping("{id}/requirements/{requirementId}/accounts")
    public RequirementAccountCreateResponse createRequirementAccount(@PathVariable String id, @PathVariable String requirementId,
                                                                     @RequestBody RequirementAccountCreateRequest request) {
        RequirementAccount requirementAccount = RequirementAccountMapper.INSTANCE.createRequestToCollection(request);
        return RequirementAccountMapper.INSTANCE.collectionToCreateResponse(requirementAccountService.save(requirementAccount));
    }

    @GetMapping("{id}/requirements/{requirementId}/accounts/{requirementAccountId}")
    public RequirementAccountGetByIdResponse getRequirementAccountById(@PathVariable String id, @PathVariable String requirementId,
                                                                       @PathVariable String requirementAccountId) {
        return RequirementAccountMapper.INSTANCE.collectionToGetById(requirementAccountService.getById(requirementAccountId));
    }

    @PutMapping("{id}/requirements/{requirementId}/accounts")
    public RequirementAccountUpdateResponse updateRequirementAccount(@PathVariable String id, @PathVariable String requirementId,
                                                                     @RequestBody RequirementAccountUpdateRequest request) {
        RequirementAccount requirementAccount = RequirementAccountMapper.INSTANCE.updateRequestToCollection(request);
        return RequirementAccountMapper.INSTANCE.collectionToUpdateResponse(requirementAccountService.update(requirementAccount));
    }

    @DeleteMapping("{id}/requirements/{requirementId}/accounts/{requirementAccountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirementAccountById(@PathVariable String id, @PathVariable String requirementId,
                                             @PathVariable String requirementAccountId) {
        requirementAccountService.deleteById(requirementAccountId);
    }

    @PostMapping("{id}/requirements/{requirementId}/scripts")
    public void saveScript(@PathVariable String id, @PathVariable String requirementId,
                           @RequestBody RequirementScriptCreateRequest request) {
        RequirementScript requirementScript = RequirementScriptMapper.INSTANCE.requestToEntity(request);
        requirementScriptService.save(requirementScript);
    }

    @GetMapping("{id}/requirements/{requirementId}/scripts/{scriptId}")
    public RequirementScriptGetByIdResponse getScriptById(@PathVariable String id, @PathVariable String requirementId,
                                                          @PathVariable String scriptId) {
        return RequirementScriptMapper.INSTANCE.entityToResponse(requirementScriptService.getById(scriptId));
    }

    @PostMapping("{id}/requirements/{requirementId}/scripts/execute/{scriptId}")
    public RequirementScriptExecuteResponse executeScript(@PathVariable String id, @PathVariable String requirementId,
                                                          @PathVariable String scriptId, @RequestBody RequirementScriptExecuteRequest request) {
        RequirementScriptExecuteDto dto = RequirementScriptMapper.INSTANCE.requestToDto(request);
        return new RequirementScriptExecuteResponse(requirementScriptService.executeScript(scriptId, dto));
    }

    @DeleteMapping("{id}/requirements/{requirementId}/scripts/{scriptId}")
    public void deleteScript(@PathVariable String scriptId) {
        requirementScriptService.deleteScript(scriptId);
    }

}
