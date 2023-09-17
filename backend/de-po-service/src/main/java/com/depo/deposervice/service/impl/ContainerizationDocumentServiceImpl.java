package com.depo.deposervice.service.impl;

import com.depo.deposervice.collection.ContainerizationDocument;
import com.depo.deposervice.exception.ContainerizationDocumentNotFoundException;
import com.depo.deposervice.repository.ContainerizationDocumentRepository;
import com.depo.deposervice.service.ContainerizationDocumentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("containerizationDocumentServiceImpl")
public class ContainerizationDocumentServiceImpl implements ContainerizationDocumentService {

    private final ContainerizationDocumentRepository repository;

    public ContainerizationDocumentServiceImpl(ContainerizationDocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ContainerizationDocument> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ContainerizationDocument> getAllByIds(List<String> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<ContainerizationDocument> getDocuments(List<String> requirementsWithVersions, List<String> requirementNames) {
        List<ContainerizationDocument> documents = repository.findAllByRequirementNameIn(requirementNames);
        List<ContainerizationDocument> documentsResponse = new ArrayList<>();
        requirementsWithVersions.forEach(v -> {
            documents.forEach(d -> {
                String requirementKey = d.getRequirementName() + ":" + d.getRequirementVersion();
                if (v.contains(requirementKey) || requirementKey.indexOf(':') != -1) {
                        if (documentsResponse.stream().noneMatch(dl -> dl.getRequirementName().equals(d.getRequirementName()))) {
                            documentsResponse.add(d);
                        }
                    }
            });
        });
        return documentsResponse;
    }

    @Override
    public ContainerizationDocument getById(String id) {
        return repository.findById(id).orElseThrow(ContainerizationDocumentNotFoundException::new);
    }

    @Override
    public ContainerizationDocument save(ContainerizationDocument containerizationDocument) {
        return repository.save(containerizationDocument);
    }

    @Override
    public ContainerizationDocument update(ContainerizationDocument containerizationDocument) {
        if (repository.existsById(containerizationDocument.getId())) {
            return repository.save(containerizationDocument);
        }
        throw new ContainerizationDocumentNotFoundException();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public ContainerizationDocument getByRequirementNameAndVersion(String requirementName, String requirementVersion) {
        return repository.findByRequirementNameAndRequirementVersion(requirementName, requirementVersion);
    }

    @Override
    public void deleteByRequirement(String requirementName, String requirementVersion) {
        repository.deleteByRequirementNameAndRequirementVersion(requirementName, requirementVersion);
    }
}
