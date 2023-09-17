package com.depo.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodebaseDto {
    private String id;
    private String name;
    private String description;
    private String projectId;
    private String userId;
}
