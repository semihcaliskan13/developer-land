package com.depo.apigateway.dto;


import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCredential {
    private String id;
    private String username;
    private String password;
    private boolean nonExpired;
    private List<RoleGetAllResponse> roles;
    private List<AuthorityGetAllResponse> authorities;

}
