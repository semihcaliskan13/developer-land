package com.depo.apigateway.repository;

import com.depo.apigateway.entity.UserCodebase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserCodebaseRepository extends JpaRepository<UserCodebase, String> {
    List<UserCodebase> findAllByCodebaseId(String codebaseId);

    List<UserCodebase> findAllByUserIdAndResponsible(String userId, boolean isResponsible);

    @Transactional
    void deleteAllByCodebaseId(String id);

    @Transactional
    void deleteByCodebaseIdAndUserId(String id, String userId);
}
