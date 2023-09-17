package com.depo.apigateway.dto.request.codebase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodebaseCreateRequest {
    private String name;
    private String description;
    private String projectId;
    private String userId;
}
