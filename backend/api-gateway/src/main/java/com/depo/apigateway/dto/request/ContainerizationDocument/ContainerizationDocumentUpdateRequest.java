package com.depo.apigateway.dto.request.ContainerizationDocument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerizationDocumentUpdateRequest {
    private String id;
    private String name;
    private byte[] file;
    private String requirementName;
    private String requirementVersion;
}
