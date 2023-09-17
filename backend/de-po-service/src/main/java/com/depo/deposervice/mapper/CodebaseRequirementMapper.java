package com.depo.deposervice.mapper;

import com.depo.deposervice.dto.request.codebase.CodebaseRequirementLinkRequest;
import com.depo.deposervice.entity.CodebaseRequirement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CodebaseRequirementMapper {

    CodebaseRequirementMapper INSTANCE = Mappers.getMapper(CodebaseRequirementMapper.class);

    List<CodebaseRequirement> linkRequestToCodebaseRequirement(List<CodebaseRequirementLinkRequest> request);
}
