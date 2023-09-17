package com.depo.apigateway.dto.response.user;

import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
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
public class UserGetByIdResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<RoleGetAllResponse> roles;
    private List<AuthorityGetAllResponse> authorities;
}
