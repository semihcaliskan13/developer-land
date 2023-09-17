package com.depo.apigateway.dto.request.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProjectUpdateRequest {
    private String userId;
    private String projectId;
}
