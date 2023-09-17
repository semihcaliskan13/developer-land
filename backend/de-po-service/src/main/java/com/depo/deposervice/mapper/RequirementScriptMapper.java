package com.depo.deposervice.mapper;

import com.depo.deposervice.collection.RequirementScript;
import com.depo.deposervice.dto.RequirementScriptExecuteDto;
import com.depo.deposervice.dto.request.RequirementScript.RequirementScriptCreateRequest;
import com.depo.deposervice.dto.request.RequirementScript.RequirementScriptExecuteRequest;
import com.depo.deposervice.dto.response.RequirementScript.RequirementScriptGetByIdResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequirementScriptMapper {
    RequirementScriptMapper INSTANCE = Mappers.getMapper(RequirementScriptMapper.class);

    RequirementScriptExecuteDto requestToDto(RequirementScriptExecuteRequest request);
    RequirementScriptGetByIdResponse entityToResponse(RequirementScript requirementScript);
    RequirementScript requestToEntity(RequirementScriptCreateRequest requirementScript);
}
