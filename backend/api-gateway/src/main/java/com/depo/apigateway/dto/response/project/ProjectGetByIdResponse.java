package com.depo.apigateway.dto.response.project;

import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProjectGetByIdResponse {
    private String id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<UserGetAllResponse> users;
}
