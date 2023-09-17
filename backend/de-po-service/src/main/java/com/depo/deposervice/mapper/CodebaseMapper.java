package com.depo.deposervice.mapper;


import com.depo.deposervice.dto.request.codebase.CodebaseCreateRequest;
import com.depo.deposervice.dto.request.codebase.CodebaseUpdateRequest;
import com.depo.deposervice.dto.response.codebase.CodebaseByProjectIdResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseCreateResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseGetAllResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseGetByIdResponse;
import com.depo.deposervice.dto.response.codebase.CodebaseUpdateResponse;
import com.depo.deposervice.entity.Codebase;
import com.depo.deposervice.entry.CodebaseEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CodebaseMapper {
    CodebaseMapper INSTANCE = Mappers.getMapper(CodebaseMapper.class);
    Codebase createRequestToCodeBase(CodebaseCreateRequest codebaseCreateRequest);
    Codebase updateRequestToCodeBase(CodebaseUpdateRequest codebaseUpdateRequest);
    CodebaseCreateResponse codebaseToCreateResponse(Codebase codebase);
    CodebaseUpdateResponse codebaseToUpdateResponse(Codebase codebase);

    CodebaseGetByIdResponse codebaseToCodeBaseGetByIdResponse(Codebase codebase);
    List<CodebaseGetAllResponse> codebaseToGetAllCodeBasesResponse(List<Codebase> codebases);
    List<CodebaseByProjectIdResponse> codebaseToByProjectIdResponse(List<Codebase> codebases);

    Codebase codebaseEntryToCodebase(CodebaseEntry codebaseEntry);
    List<Codebase> codebaseEntryToCodebase(List<CodebaseEntry> codebaseEntries);


}
