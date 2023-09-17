package com.depo.deposervice.service;

import com.depo.deposervice.entity.Requirement;

import java.util.List;

public interface RequirementService {
    Requirement getById(String id);
    List<Requirement> getAll();
    Requirement save(Requirement requirement);
    Requirement update(Requirement requirement);
    void delete(String id);
    List<Requirement> findAllById(List<String> Ids);
}
