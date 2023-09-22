package com.depo.apigateway.controller;

import com.depo.apigateway.dto.CodebaseDto;
import com.depo.apigateway.dto.CodebaseRequirementLinkDto;
import com.depo.apigateway.dto.RequirementAccountDto;
import com.depo.apigateway.dto.request.ContainerizationDocument.ContainerizationDocumentRequirementCreateRequest;
import com.depo.apigateway.dto.request.ContainerizationDocument.ContainerizationDocumentRequirementUpdateRequest;
import com.depo.apigateway.dto.request.RequirementAccount.RequirementAccountCreateRequest;
import com.depo.apigateway.dto.request.RequirementAccount.RequirementAccountUpdateRequest;
import com.depo.apigateway.dto.request.RequirementScript.RequirementScriptCreateRequest;
import com.depo.apigateway.dto.request.RequirementScript.RequirementScriptExecuteRequest;
import com.depo.apigateway.dto.request.authority.AuthorityCreateRequest;
import com.depo.apigateway.dto.request.codebase.CodebaseCreateRequest;
import com.depo.apigateway.dto.request.codebase.CodebaseRequirementLinkRequest;
import com.depo.apigateway.dto.request.codebase.CodebaseUpdateRequest;
import com.depo.apigateway.dto.request.codebase.UserCodebaseUpdateRequest;
import com.depo.apigateway.dto.response.ContainerizationDocument.ContainerizationDocumentCombinedFileResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountCreateResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountGetByIdResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountUpdateResponse;
import com.depo.apigateway.dto.response.RequirementScript.RequirementScriptExecuteResponse;
import com.depo.apigateway.dto.response.RequirementScript.RequirementScriptGetByIdResponse;
import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseCreateResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseGetByIdResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseUpdateResponse;
import com.depo.apigateway.dto.response.requirement.RequirementGetAllResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import com.depo.apigateway.entity.UserCodebase;
import com.depo.apigateway.mapper.AuthorityMapper;
import com.depo.apigateway.mapper.CodebaseDtoMapper;
import com.depo.apigateway.mapper.RequirementAccountMapper;
import com.depo.apigateway.mapper.UserCodebaseMapper;
import com.depo.apigateway.service.AuthorityService;
import com.depo.apigateway.service.CodebaseService;
import com.depo.apigateway.service.UserCodeBaseService;
import com.depo.apigateway.service.UserService;
import com.depo.apigateway.util.RequirementAccountIdGenerator;
import com.depo.apigateway.util.RequirementScriptIdGenerator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/codebases")
@SecurityRequirement(name="bearerAuth")
public class CodebaseController {

    private final CodebaseService codebaseService;
    private final UserCodeBaseService userCodeBaseService;
    private final UserService userService;
    private final AuthorityService authorityService;

    public CodebaseController(CodebaseService codebaseService, UserCodeBaseService userCodeBaseService, UserService userService, AuthorityService authorityService) {
        this.codebaseService = codebaseService;
        this.userCodeBaseService = userCodeBaseService;
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public List<CodebaseGetAllResponse> getAllCodebases() {
        return codebaseService.getAllCodebases();
    }

    @GetMapping("{id}")
    public CodebaseGetByIdResponse getCodebaseById(@PathVariable String id, @RequestParam @Nullable Boolean includeUsers) {
        CodebaseGetByIdResponse codebase = codebaseService.getCodebaseById(id);

        if (Objects.equals(includeUsers, true)) {
            List<UserCodebase> userCodebases = userCodeBaseService.getAllByCodebaseId(id);
            List<String> userIds = userCodebases.stream().map(UserCodebase::getUserId).toList();

            List<UserGetAllResponse> responseUsers = userService.getUsersById(userIds);

            List<UserGetAllResponse> responsibles = new ArrayList<>();
            List<UserGetAllResponse> members = new ArrayList<>();

            for (UserCodebase userCodebase : userCodebases) {
                responseUsers
                        .forEach(u -> {
                            if (u.getId().equals(userCodebase.getUserId()) && userCodebase.isResponsible()) {
                                responsibles.add(u);
                            } else if (u.getId().equals(userCodebase.getUserId())) {
                                members.add(u);
                            }
                        });
            }
            codebase.setResponsibles(responsibles);
            codebase.setMembers(members);
            return codebase;
        }
        return codebase;
    }

    @GetMapping("{id}/requirements")
    public List<RequirementGetAllResponse> getRequirementsByCodebase(@PathVariable String id) {
        return codebaseService.getRequirementsByCodebaseId(id);
    }

    @PutMapping("{id}/requirements")
    public void linkCodebaseWithRequirements(@PathVariable String id, @RequestBody List<CodebaseRequirementLinkRequest> request) {
        List<CodebaseRequirementLinkDto> codebaseDtos = CodebaseDtoMapper.INSTANCE.linkRequestToCodebase(request);
        codebaseService.linkCodebaseWithRequirements(id, codebaseDtos);
    }

    @PostMapping
    public CodebaseCreateResponse createCodebase(@RequestBody CodebaseCreateRequest request) {
        CodebaseDto codebaseDto = CodebaseDtoMapper.INSTANCE.createRequestToCodeBase(request);
        CodebaseCreateResponse response = codebaseService.createCodebase(codebaseDto);
        userCodeBaseService.create(new UserCodebase(request.getUserId(), response.getId(), true));
        return response;
    }

    @PutMapping
    public CodebaseUpdateResponse updateCodebase(@RequestBody CodebaseUpdateRequest request) {
        CodebaseDto codebaseDto = CodebaseDtoMapper.INSTANCE.updateRequestToCodeBase(request);
        return codebaseService.updateCodebase(codebaseDto);
    }

    @PutMapping("{id}/users")
    public void linkUsersToCodebase(@PathVariable String id, @RequestBody List<UserCodebaseUpdateRequest> request) {
        List<UserCodebase> userCodebases = userCodeBaseService.getAllByCodebaseId(id);
        List<UserCodebase> updatedUserCodebases = UserCodebaseMapper.INSTANCE.updateRequestToUserCodebases(request);

        List<UserCodebase> toCreate = updatedUserCodebases.stream()
                .filter(updated -> userCodebases.stream()
                        .noneMatch(old -> old.getUserId().equals(updated.getUserId())
                                && old.getCodebaseId().equals(updated.getCodebaseId())
                                && old.isResponsible() == updated.isResponsible()))
                .toList();
        userCodeBaseService.updateAll(toCreate);

        List<UserCodebase> toDelete = userCodebases.stream()
                .filter(old -> updatedUserCodebases.stream()
                        .noneMatch(updated -> updated.getUserId().equals(old.getUserId())
                                && updated.getCodebaseId().equals(old.getCodebaseId())
                                && updated.isResponsible() == old.isResponsible()))
                .toList();

        toDelete.forEach(userCodebase -> userCodeBaseService.delete(userCodebase.getCodebaseId(), userCodebase.getUserId()));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCodeBase(@PathVariable String id) {
        codebaseService.deleteCodebase(id);
        userCodeBaseService.deleteAll(id);
    }

    @DeleteMapping("{id}/requirements/{requirementId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirementsFromCodebase(@PathVariable String id, @PathVariable String requirementId) {
        codebaseService.deleteRequirementsFromCodebase(id, requirementId);
    }

    @DeleteMapping("{id}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserCodebase(@PathVariable String id, @PathVariable String userId) {
        userCodeBaseService.delete(id, userId);
    }

    @GetMapping("{id}/authorities")
    public List<AuthorityGetAllResponse> getAuthoritiesByCodebase(@PathVariable String id) {
        CodebaseGetByIdResponse codebase = codebaseService.getCodebaseById(id);
        return AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAuthoritiesByCodebaseName(codebase.getName()));
    }

    @PutMapping("{id}/authorities")
    public void linkCodebaseWithAuthorities(@PathVariable String id, @RequestBody List<AuthorityCreateRequest> request) {
        CodebaseGetByIdResponse codebase = codebaseService.getCodebaseById(id);
        List<AuthorityGetAllResponse> authorities = AuthorityMapper.INSTANCE
                .authorityToGetAllResponse(authorityService.getAuthoritiesByCodebaseName(codebase.getName()));

        List<AuthorityCreateRequest> authoritiesToCreate = request.stream()
                .filter(r -> authorities.stream().noneMatch(a -> a.getAuthority().equals(r.getAuthority())))
                .toList();
        authoritiesToCreate.stream()
                .map(AuthorityMapper.INSTANCE::authorityCreateRequestToAuthority).forEach(authorityService::createAuthority);

        List<String> authoritiesToDelete = authorities.stream()
                .filter(a -> request.stream().noneMatch(r -> r.getAuthority().equals(a.getAuthority())))
                .map(AuthorityGetAllResponse::getId)
                .toList();
        authoritiesToDelete.forEach(authorityService::deleteAuthority);
    }

    @PostMapping("{id}/requirements/{requirementId}/containerization-documents")
    public void linkRequirementToContainerizationDocument(@PathVariable String id, @PathVariable String requirementId,
                                                          @RequestParam("file") MultipartFile file) throws IOException {
        ContainerizationDocumentRequirementCreateRequest request = new ContainerizationDocumentRequirementCreateRequest(file.getOriginalFilename(), file.getBytes());
        codebaseService.saveContainerizationDocumentToRequirement(id, requirementId, request);
    }

    @PutMapping("{id}/requirements/{requirementId}/containerization-documents")
    public void updateContainerizationDocumentComposeFile(@PathVariable String id, @PathVariable String requirementId,
                                                          @RequestParam("file") MultipartFile file) throws IOException {
        ContainerizationDocumentRequirementUpdateRequest request = new ContainerizationDocumentRequirementUpdateRequest(file.getOriginalFilename(), file.getBytes());
        codebaseService.updateContainerizationDocumentRequirement(id, requirementId, request);
    }

    @DeleteMapping("{id}/requirements/{requirementId}/containerization-documents")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContainerizationDocument(@PathVariable String id, @PathVariable String requirementId) {
        codebaseService.deleteContainerizationDocumentFromRequirement(id, requirementId);
    }

    @GetMapping("{id}/requirements/containerization-documents")
    public ResponseEntity<ContainerizationDocumentCombinedFileResponse> getContainerizationDocumentByRequirements(@PathVariable String id, @Nullable @RequestParam("requirementId") List<String> requirementIds) {
        return codebaseService.getContainerizationDocumentByRequirements(id, requirementIds);
    }

    @PostMapping("{id}/requirements/{requirementId}/accounts")
    public RequirementAccountCreateResponse createRequirementAccount(@PathVariable String id, @PathVariable String requirementId,
                                                                     @RequestParam("userId") String userId,
                                                                     @RequestBody RequirementAccountCreateRequest request) {
        RequirementAccountDto requirementAccount = RequirementAccountMapper.INSTANCE.createRequestToCollection(request);
        requirementAccount.setId(RequirementAccountIdGenerator.generateUserAccountId(userId, requirementId));
        return codebaseService.createRequirementAccount(id, requirementId, requirementAccount);
    }

    @GetMapping("{id}/requirements/{requirementId}/accounts")
    public RequirementAccountGetByIdResponse getRequirementAccountById(@PathVariable String id, @PathVariable String requirementId,
                                                                       @RequestParam("userId") String userId) {
        String requirementAccountId = RequirementAccountIdGenerator.generateUserAccountId(userId, requirementId);
        return codebaseService.getRequirementAccountById(id, requirementId, requirementAccountId);
    }

    @PutMapping("{id}/requirements/{requirementId}/accounts")
    public RequirementAccountUpdateResponse createRequirementAccount(@PathVariable String id, @PathVariable String requirementId,
                                                                     @RequestParam("userId") String userId,
                                                                     @RequestBody RequirementAccountUpdateRequest request) {
        RequirementAccountDto requirementAccount = RequirementAccountMapper.INSTANCE.updateRequestToCollection(request);
        requirementAccount.setId(RequirementAccountIdGenerator.generateUserAccountId(userId, requirementId));
        return codebaseService.updateRequirementAccount(id, requirementId, requirementAccount);
    }

    @DeleteMapping("{id}/requirements/{requirementId}/accounts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirementAccountById(@PathVariable String id, @PathVariable String requirementId,
                                             @RequestParam("userId") String userId) {
        String requirementAccountId = RequirementAccountIdGenerator.generateUserAccountId(userId, requirementId);
        codebaseService.deleteRequirementAccountById(id, requirementId, requirementAccountId);
    }

    @PostMapping("{id}/requirements/{requirementId}/scripts")
    public void saveRequirementScript(@PathVariable String id, @PathVariable String requirementId, @RequestBody RequirementScriptCreateRequest request) {
        String requirementScriptId = RequirementScriptIdGenerator.generateRequirementScriptId(id,requirementId);
        request.setId(requirementScriptId);
        codebaseService.saveRequirementScript(id, requirementId, request);
    }

    @GetMapping("{id}/requirements/{requirementId}/scripts")
    public RequirementScriptGetByIdResponse getRequirementScriptById(@PathVariable String id, @PathVariable String requirementId) {
        String requirementScriptId = RequirementScriptIdGenerator.generateRequirementScriptId(id,requirementId);
        return codebaseService.getRequirementScriptById(id, requirementId, requirementScriptId);
    }

    @PostMapping("{id}/requirements/{requirementId}/scripts/execute")
    public RequirementScriptExecuteResponse executeRequirementScript(@PathVariable String id, @PathVariable String requirementId,
                                                               @RequestBody RequirementScriptExecuteRequest request) {
        String requirementScriptId = RequirementScriptIdGenerator.generateRequirementScriptId(id,requirementId);
        return codebaseService.executeRequirementScript(id, requirementId, requirementScriptId, request);
    }

    @DeleteMapping("{id}/requirements/{requirementId}/scripts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequirementScript(@PathVariable String id, @PathVariable String requirementId) {
        String requirementScriptId = RequirementScriptIdGenerator.generateRequirementScriptId(id,requirementId);
        codebaseService.deleteRequirementScriptById(requirementScriptId);
    }

}


