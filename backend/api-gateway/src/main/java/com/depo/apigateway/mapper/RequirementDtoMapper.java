package com.depo.apigateway.mapper;


import com.depo.apigateway.dto.RequirementDto;
import com.depo.apigateway.dto.request.requirement.RequirementCreateRequest;
import com.depo.apigateway.dto.request.requirement.RequirementUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RequirementDtoMapper {
    RequirementDtoMapper INSTANCE = Mappers.getMapper(RequirementDtoMapper.class);

    RequirementDto createRequirementRequestToRequirement(RequirementCreateRequest request);

    RequirementDto updateRequirementRequestToRequirement(RequirementUpdateRequest request);
}
