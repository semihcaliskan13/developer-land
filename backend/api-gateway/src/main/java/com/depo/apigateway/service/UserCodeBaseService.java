package com.depo.apigateway.service;

import com.depo.apigateway.entity.UserCodebase;

import java.util.List;

public interface UserCodeBaseService {


    List<UserCodebase> getAllByCodebaseId(String id);

    UserCodebase create(UserCodebase userCodebase);

    List<UserCodebase> getAllByUserIdAndIsResponsible(String userId, boolean isResponsible);

    void updateAll(List<UserCodebase> userCodebases);

    void deleteAll(String id);

    void delete(String id, String userId);
}
