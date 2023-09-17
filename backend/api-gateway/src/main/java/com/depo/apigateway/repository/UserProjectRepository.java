package com.depo.apigateway.repository;

import com.depo.apigateway.entity.UserProject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, String> {
    List<UserProject> findAllByProjectId(String projectId);

    List<UserProject> findAllByProjectIdIn(List<String> ids);

    @Transactional
    void deleteAllByProjectId(String id);

    @Transactional
    void deleteByProjectIdAndUserId(String projectId, String userId);
}
