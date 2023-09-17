package com.depo.userservice.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
}
