package com.depo.userservice.dto.response.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenLoginResponse {
    private String accessToken;
    private String refreshToken;
}
