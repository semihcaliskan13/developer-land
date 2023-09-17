package com.depo.deposervice.service.impl;


import com.depo.deposervice.entity.Requirement;
import com.depo.deposervice.exception.RequirementNotFoundException;
import com.depo.deposervice.repository.RequirementRepository;
import com.depo.deposervice.service.RequirementService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("requirementServiceImpl")
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepository requirementRepository;

    public RequirementServiceImpl(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @Override
    public Requirement getById(String id) {
        return requirementRepository.findById(id).orElseThrow(RequirementNotFoundException::new);
    }

    @Override
    public List<Requirement> getAll() {
       return requirementRepository.findAll();
    }

    @Override
    public Requirement save(Requirement requirement) {
        return requirementRepository.save(requirement);
    }

    @Override
    public Requirement update(Requirement requirement) {
        if (requirementRepository.existsById(requirement.getId())){
           return requirementRepository.save(requirement);
        }
        throw new RequirementNotFoundException();
    }

    @Override
    public void delete(String id) {
        requirementRepository.deleteById(id);
    }

    @Override
    public List<Requirement> findAllById(List<String> Ids) {
        return requirementRepository.findAllById(Ids);
    }
}
