package com.depo.apigateway.mapper;


import com.depo.apigateway.dto.CodebaseDto;
import com.depo.apigateway.dto.CodebaseRequirementLinkDto;
import com.depo.apigateway.dto.request.codebase.CodebaseCreateRequest;
import com.depo.apigateway.dto.request.codebase.CodebaseRequirementLinkRequest;
import com.depo.apigateway.dto.request.codebase.CodebaseUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface CodebaseDtoMapper {
    CodebaseDtoMapper INSTANCE = Mappers.getMapper(CodebaseDtoMapper.class);

    CodebaseDto createRequestToCodeBase(CodebaseCreateRequest codebaseCreateRequest);

    CodebaseDto updateRequestToCodeBase(CodebaseUpdateRequest codebaseUpdateRequest);

    List<CodebaseRequirementLinkDto> linkRequestToCodebase(List<CodebaseRequirementLinkRequest> request);
}
