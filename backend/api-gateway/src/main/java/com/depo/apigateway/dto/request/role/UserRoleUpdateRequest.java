package com.depo.apigateway.dto.request.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleUpdateRequest {
    private String roleId;
    private String userId;
}
