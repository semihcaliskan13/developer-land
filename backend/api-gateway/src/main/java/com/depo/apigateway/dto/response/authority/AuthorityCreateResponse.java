package com.depo.apigateway.dto.response.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorityCreateResponse {
    private String id;
    private String authority;
    private LocalDateTime cratedDate;
    private LocalDateTime updatedDate;
}
