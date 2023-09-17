package com.depo.apigateway.dto.response.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleCreateResponse {
    private String id;
    private String role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
