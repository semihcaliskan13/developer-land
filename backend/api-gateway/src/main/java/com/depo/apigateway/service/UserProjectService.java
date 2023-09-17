package com.depo.apigateway.service;

import com.depo.apigateway.entity.UserProject;

import java.util.List;

public interface UserProjectService {
    List<UserProject> findAllByProjectId(String projectId);

    List<UserProject> findAllByProjectId(List<String> projectIds);

    UserProject create(UserProject userProject);

    void deleteAll(String id);

    void updateAll(List<UserProject> userProjects);

    void delete(String id, String userId);
}
