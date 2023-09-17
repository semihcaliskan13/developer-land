package com.depo.deposervice.mapper;


import com.depo.deposervice.dto.request.requirement.RequirementCreateRequest;
import com.depo.deposervice.dto.request.requirement.RequirementUpdateRequest;
import com.depo.deposervice.dto.response.requirement.RequirementCreateResponse;
import com.depo.deposervice.dto.response.requirement.RequirementGetAllResponse;
import com.depo.deposervice.dto.response.requirement.RequirementGetByIdResponse;
import com.depo.deposervice.dto.response.requirement.RequirementUpdateResponse;
import com.depo.deposervice.entity.Requirement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RequirementMapper {
    RequirementMapper INSTANCE = Mappers.getMapper(RequirementMapper.class);

    Requirement createRequirementRequestToRequirement(RequirementCreateRequest request);
    Requirement updateRequirementRequestToRequirement(RequirementUpdateRequest request);
    RequirementCreateResponse requirementToCreateResponse(Requirement requirement);
    RequirementUpdateResponse requirementToUpdateResponse(Requirement requirement);
    RequirementGetByIdResponse requirementToGetByIdRequirement(Requirement request);
    List<RequirementGetAllResponse> requirementToGetAllRequirements(List<Requirement> request);

}
