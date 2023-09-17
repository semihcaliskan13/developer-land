package com.depo.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
}
