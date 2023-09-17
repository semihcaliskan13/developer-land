package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.UserCodebase;
import com.depo.apigateway.repository.UserCodebaseRepository;
import com.depo.apigateway.service.UserCodeBaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("userCodebaseServiceImpl")
@ConditionalOnProperty(name = "data.source.location", havingValue = "db")
public class UserCodebaseServiceImpl implements UserCodeBaseService {

    private final UserCodebaseRepository userCodebaseRepository;

    public UserCodebaseServiceImpl(UserCodebaseRepository userCodebaseRepository) {
        this.userCodebaseRepository = userCodebaseRepository;
    }

    @Override
    public List<UserCodebase> getAllByCodebaseId(String id) {
        return userCodebaseRepository.findAllByCodebaseId(id);
    }

    @Override
    public UserCodebase create(UserCodebase userCodebase) {
        return userCodebaseRepository.save(userCodebase);
    }

    @Override
    public List<UserCodebase> getAllByUserIdAndIsResponsible(String userId, boolean isResponsible) {
        return userCodebaseRepository.findAllByUserIdAndResponsible(userId, isResponsible);
    }

    @Override
    public void updateAll(List<UserCodebase> userCodebases) {
        userCodebaseRepository.saveAll(userCodebases);
    }

    @Override
    public void deleteAll(String id) {
        userCodebaseRepository.deleteAllByCodebaseId(id);
    }

    @Override
    public void delete(String id, String userId) {
        userCodebaseRepository.deleteByCodebaseIdAndUserId(id, userId);
    }
}
