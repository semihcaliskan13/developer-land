package com.depo.apigateway.dto.response.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProjectUpdateResponse {
    private String id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
