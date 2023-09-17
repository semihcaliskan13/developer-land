package com.depo.apigateway.mapper;

import com.depo.apigateway.dto.RequirementAccountDto;
import com.depo.apigateway.dto.request.RequirementAccount.RequirementAccountCreateRequest;
import com.depo.apigateway.dto.request.RequirementAccount.RequirementAccountUpdateRequest;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountGetAllResponse;
import com.depo.apigateway.dto.response.RequirementAccount.RequirementAccountGetByIdResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RequirementAccountMapper {
    RequirementAccountMapper INSTANCE = Mappers.getMapper(RequirementAccountMapper.class);

    List<RequirementAccountGetAllResponse> collectionToGetAll(List<RequirementAccountDto> requirementAccounts);

    RequirementAccountGetByIdResponse collectionToGetById(RequirementAccountDto requirementAccount);

    RequirementAccountDto createRequestToCollection(RequirementAccountCreateRequest request);
    RequirementAccountDto updateRequestToCollection(RequirementAccountUpdateRequest request);
}
