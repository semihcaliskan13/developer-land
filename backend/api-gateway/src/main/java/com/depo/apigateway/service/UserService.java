package com.depo.apigateway.service;


import com.depo.apigateway.dto.UserCredential;
import com.depo.apigateway.dto.UserDto;
import com.depo.apigateway.dto.response.user.UserCreateResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import com.depo.apigateway.dto.response.user.UserGetByIdResponse;
import com.depo.apigateway.dto.response.user.UserUpdateResponse;
import com.depo.apigateway.util.CustomFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", path = "/api/v1/users", configuration = CustomFeignClient.class)
public interface UserService {
    @GetMapping
    List<UserGetAllResponse> getAllUsers();

    @GetMapping("{id}")
    UserGetByIdResponse getUserById(@PathVariable String id);

    @GetMapping
    List<UserGetAllResponse> getUsersById(@RequestParam List<String> ids);

    @GetMapping("/credentials/{username}")
    UserCredential getUserCredentialsByUsername(@PathVariable String username);

    @GetMapping("/username/{username}")
    UserGetByIdResponse getUserByUsername(@PathVariable String username);

    @PostMapping
    UserCreateResponse createUser(@RequestBody UserDto request);

    @PutMapping
    UserUpdateResponse updateUser(@RequestBody UserDto request);

    @DeleteMapping("{id}")
    Void deleteUser(@PathVariable String id);
}
