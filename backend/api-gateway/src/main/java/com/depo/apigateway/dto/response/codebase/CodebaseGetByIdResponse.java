package com.depo.apigateway.dto.response.codebase;

import com.depo.apigateway.dto.response.project.ProjectGetByIdResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<UserGetAllResponse> members;
    private List<UserGetAllResponse> responsibles;
}
