package com.depo.apigateway.repository;

import com.depo.apigateway.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    List<UserRole> findAllByRoleId(String roleId);

    List<UserRole> findAllByUserId(String userId);

    List<UserRole> findAllByUserIdIn(List<String> userIds);

    @Transactional
    void deleteAllByRoleId(String roleId);

    @Transactional
    void deleteAllByUserId(String userId);

    @Transactional
    void deleteByRoleIdAndUserId(String roleId, String userId);

}
