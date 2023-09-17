package com.depo.apigateway.dto.response.requirement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementCreateResponse {
    private String id;
    private String name;
    private String version;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
