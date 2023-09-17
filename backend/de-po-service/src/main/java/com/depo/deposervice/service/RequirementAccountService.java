package com.depo.deposervice.service;

import com.depo.deposervice.collection.RequirementAccount;

import java.util.List;

public interface RequirementAccountService {

    List<RequirementAccount> getAll();
    RequirementAccount getById(String id);
    RequirementAccount save(RequirementAccount requirementAccount);
    RequirementAccount update(RequirementAccount requirementAccount);
    void deleteById(String id);
}
