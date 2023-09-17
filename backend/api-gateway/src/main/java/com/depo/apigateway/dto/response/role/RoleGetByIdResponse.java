package com.depo.apigateway.dto.response.role;

import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
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
public class RoleGetByIdResponse {
    private String id;
    private String role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<UserGetAllResponse> users;
    private List<AuthorityGetAllResponse> authorities;
}
