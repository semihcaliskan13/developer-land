package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.UserRole;
import com.depo.apigateway.repository.UserRoleRepository;
import com.depo.apigateway.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> getAllByRoleId(String roleId) {
        return userRoleRepository.findAllByRoleId(roleId);
    }

    @Override
    public List<UserRole> getAllByUserId(String userId) {
        return userRoleRepository.findAllByUserId(userId);
    }

    @Override
    public List<UserRole> getAllByUserIds(List<String> userIds) {
        return userRoleRepository.findAllByUserIdIn(userIds);
    }

    @Override
    public UserRole create(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void updateAll(List<UserRole> userRoles) {
        userRoleRepository.saveAll(userRoles);
    }

    @Override
    public void deleteAllByRoleId(String roleId) {
        userRoleRepository.deleteAllByRoleId(roleId);
    }

    @Override
    public void deleteAllByUserId(String userId) {
        userRoleRepository.deleteAllByUserId(userId);
    }

    @Override
    public void delete(String roleId, String userId) {
        userRoleRepository.deleteByRoleIdAndUserId(roleId, userId);
    }
}
