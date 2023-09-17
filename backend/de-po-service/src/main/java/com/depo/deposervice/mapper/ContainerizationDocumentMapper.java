package com.depo.deposervice.mapper;

import com.depo.deposervice.collection.ContainerizationDocument;
import com.depo.deposervice.dto.request.ContainerizationDocument.ContainerizationDocumentCreateRequest;
import com.depo.deposervice.dto.request.ContainerizationDocument.ContainerizationDocumentUpdateRequest;
import com.depo.deposervice.dto.response.ContainerizationDocument.ContainerizationDocumentCreateResponse;
import com.depo.deposervice.dto.response.ContainerizationDocument.ContainerizationDocumentGetAllResponse;
import com.depo.deposervice.dto.response.ContainerizationDocument.ContainerizationDocumentGetByIdResponse;
import com.depo.deposervice.dto.response.ContainerizationDocument.ContainerizationDocumentUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContainerizationDocumentMapper {

    ContainerizationDocumentMapper INSTANCE = Mappers.getMapper(ContainerizationDocumentMapper.class);
    List<ContainerizationDocumentGetAllResponse> collectionToGetAll(List<ContainerizationDocument> files);
    ContainerizationDocumentGetByIdResponse collectionToGetById(ContainerizationDocument file);
    ContainerizationDocument createRequestToCollection(ContainerizationDocumentCreateRequest request);
    ContainerizationDocument updateRequestToCollection(ContainerizationDocumentUpdateRequest request);
    ContainerizationDocumentCreateResponse collectionToCreateResponse(ContainerizationDocument file);
    ContainerizationDocumentUpdateResponse collectionToUpdateResponse(ContainerizationDocument file);
}
