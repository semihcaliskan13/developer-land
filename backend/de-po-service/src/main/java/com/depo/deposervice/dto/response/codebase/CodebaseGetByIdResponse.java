package com.depo.deposervice.dto.response.codebase;

import com.depo.deposervice.dto.response.project.ProjectGetByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodebaseGetByIdResponse {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private ProjectGetByIdResponse project;
}
