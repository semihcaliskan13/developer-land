package com.depo.apigateway.service;

import com.depo.apigateway.entity.UserAuthority;

import java.util.List;

public interface UserAuthorityService {

    List<UserAuthority> getAllByUserId(String userId);

    List<UserAuthority> getAllByAuthorityId(String authorityId);

    UserAuthority create(UserAuthority userRole);

    void updateAll(List<UserAuthority> userRoles);

    void deleteAllByUserId(String userId);

    void deleteAllByAuthorityId(String authorityId);

    void delete(String userId, String authorityId);
}
