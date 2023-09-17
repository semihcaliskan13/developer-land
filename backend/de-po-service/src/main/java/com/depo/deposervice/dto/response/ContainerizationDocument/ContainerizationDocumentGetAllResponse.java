package com.depo.deposervice.dto.response.ContainerizationDocument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerizationDocumentGetAllResponse {
    private String id;
    private String name;
    private byte[] file;
    private String requirementName;
    private String requirementVersion;
}
