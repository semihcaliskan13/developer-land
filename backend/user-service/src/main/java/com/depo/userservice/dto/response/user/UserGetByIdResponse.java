package com.depo.userservice.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
