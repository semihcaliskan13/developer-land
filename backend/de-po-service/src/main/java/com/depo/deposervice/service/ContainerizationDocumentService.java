package com.depo.deposervice.service;

import com.depo.deposervice.collection.ContainerizationDocument;

import java.util.List;

public interface ContainerizationDocumentService {

    List<ContainerizationDocument> getAll();

    List<ContainerizationDocument> getAllByIds(List<String> ids);

    List<ContainerizationDocument> getDocuments(List<String> requirementsWithVersions, List<String> requirementNames);

    ContainerizationDocument getById(String id);

    ContainerizationDocument save(ContainerizationDocument containerizationDocument);

    ContainerizationDocument update(ContainerizationDocument containerizationDocument);

    void delete(String id);

    ContainerizationDocument getByRequirementNameAndVersion(String requirementName, String requirementVersion);

    void deleteByRequirement(String requirementName, String requirementVersion);
}
