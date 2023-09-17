package com.depo.userservice.controller;

import com.depo.userservice.dto.request.user.UserCreateRequest;
import com.depo.userservice.dto.request.user.UserUpdateRequest;
import com.depo.userservice.dto.response.user.UserCreateResponse;
import com.depo.userservice.dto.response.user.UserCredentialsResponse;
import com.depo.userservice.dto.response.user.UserGetAllResponse;
import com.depo.userservice.dto.response.user.UserGetByIdResponse;
import com.depo.userservice.dto.response.user.UserUpdateResponse;
import com.depo.userservice.entity.User;
import com.depo.userservice.mapper.UserMapper;
import com.depo.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserGetAllResponse> getUsers(@RequestParam @Nullable List<String> ids) {
        if (ids==null){
            return UserMapper.INSTANCE.userToUserGetAllResponse(userService.getAll());
        }
        return UserMapper.INSTANCE.userToUserGetAllResponse(userService.getAllByIds(ids));
    }


    @GetMapping("{id}")
    public UserGetByIdResponse getUserById(@PathVariable String id){
        return UserMapper.INSTANCE.userToUserGetByIdResponse(userService.getById(id));
    }

    @GetMapping("/credentials/{username}")
    public UserCredentialsResponse getUserCredentialsByUsername(@PathVariable String username){
        return UserMapper.INSTANCE.userToUserCredentialsResponse(userService.getByUsername(username));
    }

    @GetMapping("/username/{username}")
    public UserGetByIdResponse getUserByUsername(@PathVariable String username){
        return UserMapper.INSTANCE.userToUserGetByIdResponse(userService.getByUsername(username));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse saveUser(@RequestBody UserCreateRequest request){
        User user = UserMapper.INSTANCE.userCreateRequestToUser(request);
        user = userService.save(user);
        return UserMapper.INSTANCE.userToUserCreateResponse(user);
    }

    @PutMapping
    public UserUpdateResponse updateUser(@RequestBody @Valid UserUpdateRequest request){
        User user = UserMapper.INSTANCE.userUpdateRequestToUser(request);
        user = userService.update(user);
        return UserMapper.INSTANCE.userToUserUpdateResponse(user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id){
        userService.delete(id);
    }
}
