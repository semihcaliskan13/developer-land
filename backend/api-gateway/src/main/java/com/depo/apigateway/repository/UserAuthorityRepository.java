package com.depo.apigateway.repository;

import com.depo.apigateway.entity.UserAuthority;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String> {

    List<UserAuthority> findAllByUserId(String userId);

    List<UserAuthority> findAllByAuthorityId(String authorityId);

    @Transactional
    void deleteAllByUserId(String userId);

    @Transactional
    void deleteAllByAuthorityId(String authorityId);

    @Transactional
    void deleteByUserIdAndAuthorityId(String userId, String authorityId);
}
