package com.depo.apigateway.dto.request.codebase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodebaseRequirementLinkRequest {
    private String codebaseId;
    private String requirementId;
}
