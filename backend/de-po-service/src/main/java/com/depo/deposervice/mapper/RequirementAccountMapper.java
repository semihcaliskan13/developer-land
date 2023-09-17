package com.depo.deposervice.mapper;

import com.depo.deposervice.collection.RequirementAccount;
import com.depo.deposervice.dto.request.RequirementAccount.RequirementAccountCreateRequest;
import com.depo.deposervice.dto.request.RequirementAccount.RequirementAccountUpdateRequest;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountCreateResponse;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountGetAllResponse;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountGetByIdResponse;
import com.depo.deposervice.dto.response.RequirementAccount.RequirementAccountUpdateResponse;
import com.depo.deposervice.util.RequirementAccountUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface RequirementAccountMapper {

    RequirementAccountMapper INSTANCE = Mappers.getMapper(RequirementAccountMapper.class);

    RequirementAccountGetAllResponse collectionToGetAll(RequirementAccount requirementAccount);

    @Mapping(target = "genericAccountInfo", expression = "java(com.depo.deposervice.util.RequirementAccountUtil.stringToMap(entity.getGenericAccountInfo()))")
    RequirementAccountGetByIdResponse collectionToGetById(RequirementAccount entity);

    @Mapping(target = "genericAccountInfo", expression = "java(com.depo.deposervice.util.RequirementAccountUtil.mapToString(request.getGenericAccountInfo()))")
    RequirementAccount createRequestToCollection(RequirementAccountCreateRequest request);

    @Mapping(target = "genericAccountInfo", expression = "java(com.depo.deposervice.util.RequirementAccountUtil.mapToString(request.getGenericAccountInfo()))")
    RequirementAccount updateRequestToCollection(RequirementAccountUpdateRequest request);

    @Mapping(target = "genericAccountInfo", expression = "java(com.depo.deposervice.util.RequirementAccountUtil.stringToMap(requirementAccount.getGenericAccountInfo()))")
    RequirementAccountCreateResponse collectionToCreateResponse(RequirementAccount requirementAccount);

    @Mapping(target = "genericAccountInfo", expression = "java(com.depo.deposervice.util.RequirementAccountUtil.stringToMap(requirementAccount.getGenericAccountInfo()))")
    RequirementAccountUpdateResponse collectionToUpdateResponse(RequirementAccount requirementAccount);

    default Map<String, Object> stringToMap(String jsonString) {
        return RequirementAccountUtil.stringToMap(jsonString);
    }

    default String mapToString(Map<String, Object> map) {
        return RequirementAccountUtil.mapToString(map);
    }
}
