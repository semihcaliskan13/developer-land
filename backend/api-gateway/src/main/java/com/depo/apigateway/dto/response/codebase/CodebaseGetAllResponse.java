package com.depo.apigateway.dto.response.codebase;

import com.depo.apigateway.dto.response.project.ProjectGetByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodebaseGetAllResponse {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private ProjectGetByIdResponse project;
}
