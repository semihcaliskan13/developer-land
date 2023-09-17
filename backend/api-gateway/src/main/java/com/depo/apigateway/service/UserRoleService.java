package com.depo.apigateway.service;

import com.depo.apigateway.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllByRoleId(String roleId);

    List<UserRole> getAllByUserId(String userId);
    List<UserRole> getAllByUserIds(List<String> userIds);

    UserRole create(UserRole userRole);

    void updateAll(List<UserRole> userRoles);

    void deleteAllByRoleId(String roleId);

    void deleteAllByUserId(String userId);

    void delete(String roleId, String userId);
}
