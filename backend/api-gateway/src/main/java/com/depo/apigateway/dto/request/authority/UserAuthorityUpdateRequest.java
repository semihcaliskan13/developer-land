package com.depo.apigateway.dto.request.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuthorityUpdateRequest {
    private String userId;
    private String authorityId;
}
