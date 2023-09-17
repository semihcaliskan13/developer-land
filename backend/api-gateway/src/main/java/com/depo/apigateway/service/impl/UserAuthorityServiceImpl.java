package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.UserAuthority;
import com.depo.apigateway.repository.UserAuthorityRepository;
import com.depo.apigateway.service.UserAuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthorityServiceImpl implements UserAuthorityService {

    private final UserAuthorityRepository userAuthorityRepository;

    public UserAuthorityServiceImpl(UserAuthorityRepository userAuthorityRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
    }

    @Override
    public List<UserAuthority> getAllByUserId(String userId) {
        return userAuthorityRepository.findAllByUserId(userId);
    }

    @Override
    public List<UserAuthority> getAllByAuthorityId(String authorityId) {
        return userAuthorityRepository.findAllByAuthorityId(authorityId);
    }

    @Override
    public UserAuthority create(UserAuthority userRole) {
        return userAuthorityRepository.save(userRole);
    }

    @Override
    public void updateAll(List<UserAuthority> userRoles) {
        userAuthorityRepository.saveAll(userRoles);
    }

    @Override
    public void deleteAllByUserId(String userId) {
        userAuthorityRepository.deleteAllByUserId(userId);
    }

    @Override
    public void deleteAllByAuthorityId(String authorityId) {
        userAuthorityRepository.deleteAllByAuthorityId(authorityId);
    }

    @Override
    public void delete(String userId, String authorityId) {
        userAuthorityRepository.deleteByUserIdAndAuthorityId(userId, authorityId);
    }
}
