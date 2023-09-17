package com.depo.deposervice.service;

import com.depo.deposervice.collection.RequirementScript;
import com.depo.deposervice.dto.RequirementScriptExecuteDto;


public interface RequirementScriptService {


    RequirementScript getById(String id);
    RequirementScript save(RequirementScript requirementScript);
    String executeScript(String id, RequirementScriptExecuteDto dto);

    void deleteScript(String id);
}
