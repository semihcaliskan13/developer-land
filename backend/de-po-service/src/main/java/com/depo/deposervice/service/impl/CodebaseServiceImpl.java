package com.depo.deposervice.service.impl;


import com.depo.deposervice.entity.Codebase;
import com.depo.deposervice.exception.CodebaseNotFoundException;
import com.depo.deposervice.repository.CodebaseRepository;
import com.depo.deposervice.service.CodebaseService;
import com.depo.deposervice.service.RequirementService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("codebaseServiceImpl")
@ConditionalOnProperty(name = "data.source.location", havingValue = "db")
public class CodebaseServiceImpl implements CodebaseService {

    private final CodebaseRepository codeBaseRepository;


    public CodebaseServiceImpl(CodebaseRepository codeBaseRepository) {
        this.codeBaseRepository = codeBaseRepository;
    }

    @Override
    public Codebase getCodeBaseById(String id)  {
        return codeBaseRepository.findById(id).orElseThrow(CodebaseNotFoundException::new);
    }

    @Override
    public List<Codebase> getAllCodeBases() {
        return codeBaseRepository.findAll();
    }

    @Override
    public List<Codebase> getAllCodebasesByIds(List<String> codebaseIds) {
        return codeBaseRepository.findAllById(codebaseIds);
    }

    @Override
    public Codebase saveCodeBase(Codebase codeBase) {
        return codeBaseRepository.save(codeBase);
    }

    @Override
    public Codebase updateCodeBase(Codebase codeBase) {
        if (codeBaseRepository.existsById(codeBase.getId())){
            return codeBaseRepository.save(codeBase);

        }
        throw new CodebaseNotFoundException();
    }

    @Override
    public void deleteCodeBase(String id) {
        codeBaseRepository.deleteById(id);
    }

    @Override
    public List<Codebase> getCodeBasesByProjectId(String id) {
        return codeBaseRepository.getCodeBaseByProjectId(id);
    }

}
