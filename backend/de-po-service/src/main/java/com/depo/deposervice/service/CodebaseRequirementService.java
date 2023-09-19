package com.depo.deposervice.service;

import com.depo.deposervice.entity.CodebaseRequirement;

import java.util.List;

public interface CodebaseRequirementService {

    List<CodebaseRequirement> getAllByCodebaseId(String codebaseId);

    List<CodebaseRequirement> getAllByRequirementId(String requirementId);

    List<CodebaseRequirement> getAllByCodebaseIds(List<String> codebaseIds);

    void updateAll(List<CodebaseRequirement> codebaseRequirements);

    void save(CodebaseRequirement codebaseRequirement);

    void deleteAllByCodeBaseId(String codebaseId);

    void deleteAllByRequirementId(String requirementId);

    void delete(String codebaseId, String requirementId);
}
