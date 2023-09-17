package com.depo.deposervice.service.impl;

import com.depo.deposervice.collection.RequirementAccount;
import com.depo.deposervice.exception.RequirementAccountNotFoundException;
import com.depo.deposervice.repository.RequirementAccountRepository;
import com.depo.deposervice.service.RequirementAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementAccountServiceImpl implements RequirementAccountService {

    private final RequirementAccountRepository repository;

    public RequirementAccountServiceImpl(RequirementAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RequirementAccount> getAll() {
        return repository.findAll();
    }

    @Override
    public RequirementAccount getById(String id) {
        return repository.findById(id).orElseThrow(RequirementAccountNotFoundException::new);
    }

    @Override
    public RequirementAccount save(RequirementAccount requirementAccount) {
        return repository.save(requirementAccount);
    }

    @Override
    public RequirementAccount update(RequirementAccount requirementAccount) {
        if (repository.existsById(requirementAccount.getId())){
            return repository.save(requirementAccount);
        }
        throw new RequirementAccountNotFoundException();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
