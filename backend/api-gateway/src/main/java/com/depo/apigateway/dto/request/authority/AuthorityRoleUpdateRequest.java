package com.depo.apigateway.dto.request.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorityRoleUpdateRequest {
    private List<String> roleIds;
}
