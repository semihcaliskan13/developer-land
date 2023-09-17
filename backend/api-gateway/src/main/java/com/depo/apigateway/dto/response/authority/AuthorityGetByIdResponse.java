package com.depo.apigateway.dto.response.authority;

import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
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
public class AuthorityGetByIdResponse {
    private String id;
    private String authority;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<UserGetAllResponse> users;
    private List<RoleGetAllResponse> roles;
}
