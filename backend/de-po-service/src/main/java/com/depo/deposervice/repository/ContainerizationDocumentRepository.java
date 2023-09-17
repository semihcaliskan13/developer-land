package com.depo.deposervice.repository;

import com.depo.deposervice.collection.ContainerizationDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerizationDocumentRepository extends MongoRepository<ContainerizationDocument, String> {
    List<ContainerizationDocument> findAllByRequirementNameIn(List<String> requirements);
    ContainerizationDocument findByRequirementNameAndRequirementVersion(String requirement, String requirementVersion);

    @Transactional
    void deleteByRequirementNameAndRequirementVersion(String requirementName, String requirementVersion);

}
