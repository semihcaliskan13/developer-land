package com.depo.deposervice.repository;

import com.depo.deposervice.entity.CodebaseRequirement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodebaseRequirementRepository extends JpaRepository<CodebaseRequirement,String> {

    List<CodebaseRequirement> findAllByCodebaseIdAndSourceType(String codebaseId, String sourceType);
    List<CodebaseRequirement> findAllByCodebaseIdInAndSourceType(List<String> codebaseIds, String sourceType);
    List<CodebaseRequirement> findAllByRequirementIdAndSourceType(String requirementId, String sourceType);
    @Transactional
    void deleteAllByCodebaseIdAndSourceType(String codebaseId, String sourceType);

    @Transactional
    void deleteAllByRequirementIdAndSourceType(String requirementId, String sourceType);

    @Transactional
    void deleteByCodebaseIdAndRequirementIdAndSourceType(String codebaseId, String requirementId, String sourceType);
}