package com.depo.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerizationDocumentDto {
    private String id;
    private String name;
    private byte[] file;
}
