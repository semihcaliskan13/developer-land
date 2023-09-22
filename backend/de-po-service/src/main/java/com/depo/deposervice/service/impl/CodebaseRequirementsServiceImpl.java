package com.depo.deposervice.service.impl;

import com.depo.deposervice.entity.CodebaseRequirement;
import com.depo.deposervice.repository.CodebaseRequirementRepository;
import com.depo.deposervice.service.CodebaseRequirementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodebaseRequirementsServiceImpl implements CodebaseRequirementService {

    @Value("${data.source.location}")
    private String dataSourceLocation;

    private final CodebaseRequirementRepository repository;

    public CodebaseRequirementsServiceImpl(CodebaseRequirementRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<CodebaseRequirement> getAllByCodebaseIds(List<String> codebaseIds) {
        return repository.findAllByCodebaseIdInAndSourceType(codebaseIds, dataSourceLocation);
    }

    @Override
    public List<CodebaseRequirement> getAllByCodebaseId(String codebaseId) {
        return repository.findAllByCodebaseIdAndSourceType(codebaseId,dataSourceLocation);
    }

    @Override
    public List<CodebaseRequirement> getAllByRequirementId(String requirementId) {
        return repository.findAllByRequirementIdAndSourceType(requirementId,dataSourceLocation);
    }

    @Override
    public void updateAll(List<CodebaseRequirement> codebaseRequirements) {
        codebaseRequirements.forEach(codebaseRequirement -> codebaseRequirement.setSourceType(dataSourceLocation));
        repository.saveAll(codebaseRequirements);
    }

    @Override
    public void save(CodebaseRequirement codebaseRequirement) {
        repository.save(codebaseRequirement);
    }

    @Override
    public void deleteAllByCodeBaseId(String codebaseId) {
        repository.deleteAllByCodebaseIdAndSourceType(codebaseId, dataSourceLocation);
    }

    @Override
    public void deleteAllByRequirementId(String requirementId) {
        repository.deleteAllByRequirementIdAndSourceType(requirementId,dataSourceLocation);
    }

    @Override
    public void delete(String codebaseId, String requirementId) {
        repository.deleteByCodebaseIdAndRequirementIdAndSourceType(codebaseId, requirementId, dataSourceLocation);
    }
}