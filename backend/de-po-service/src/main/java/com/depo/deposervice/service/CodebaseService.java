package com.depo.deposervice.service;

import com.depo.deposervice.entity.Codebase;

import java.util.List;

public interface CodebaseService {

    Codebase getCodeBaseById(String id) ;
    List<Codebase> getAllCodeBases();

    List<Codebase> getAllCodebasesByIds(List<String> codebaseIds);
    Codebase saveCodeBase(Codebase codeBase);
    Codebase updateCodeBase(Codebase codeBase);
    void deleteCodeBase(String id);
    List<Codebase> getCodeBasesByProjectId(String id);
}
