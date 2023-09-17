package com.depo.deposervice.dto.request.codebase;

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
