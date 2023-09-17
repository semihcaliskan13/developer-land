package com.depo.apigateway.dto.request.ContainerizationDocument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerizationDocumentRequirementCreateRequest {
    private String name;
    private byte[] file;
}
