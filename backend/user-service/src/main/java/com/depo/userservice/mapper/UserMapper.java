package com.depo.userservice.mapper;

import com.depo.userservice.dto.request.user.UserCreateRequest;
import com.depo.userservice.dto.request.user.UserUpdateRequest;
import com.depo.userservice.dto.response.user.UserCreateResponse;
import com.depo.userservice.dto.response.user.UserCredentialsResponse;
import com.depo.userservice.dto.response.user.UserGetAllResponse;
import com.depo.userservice.dto.response.user.UserGetByIdResponse;
import com.depo.userservice.dto.response.user.UserUpdateResponse;
import com.depo.userservice.entity.User;
import com.depo.userservice.entry.UserEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userCreateRequestToUser(UserCreateRequest request);
    User userUpdateRequestToUser(UserUpdateRequest request);
    UserCreateResponse userToUserCreateResponse(User user);
    UserUpdateResponse userToUserUpdateResponse(User user);
    UserGetByIdResponse userToUserGetByIdResponse(User user);
    List<UserGetAllResponse> userToUserGetAllResponse(List<User> users);
    UserCredentialsResponse userToUserCredentialsResponse(User user);
    User userEntryToUser(UserEntry userEntry);
    List<User> userEntryToUser(List<UserEntry> userEntries);

}
