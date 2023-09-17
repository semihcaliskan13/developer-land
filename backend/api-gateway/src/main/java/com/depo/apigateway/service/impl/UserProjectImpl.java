package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.UserProject;
import com.depo.apigateway.repository.UserProjectRepository;
import com.depo.apigateway.service.UserProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("userProjectDBService")
@ConditionalOnProperty(name = "data.source.location", havingValue = "db")
public class UserProjectImpl implements UserProjectService {

    private final UserProjectRepository userProjectRepository;

    public UserProjectImpl(UserProjectRepository userProjectRepository) {
        this.userProjectRepository = userProjectRepository;
    }

    @Override
    public List<UserProject> findAllByProjectId(String projectId) {
        return userProjectRepository.findAllByProjectId(projectId);
    }

    @Override
    public List<UserProject> findAllByProjectId(List<String> projectIds) {
        return userProjectRepository.findAllByProjectIdIn(projectIds);
    }

    @Override
    public UserProject create(UserProject userProject) {
        return userProjectRepository.save(userProject);
    }

    @Override
    public void deleteAll(String id) {
        userProjectRepository.deleteAllByProjectId(id);
    }

    @Override
    public void updateAll(List<UserProject> userProjects) {
        userProjectRepository.saveAll(userProjects);
    }

    @Override
    public void delete(String id, String userId) {
        userProjectRepository.deleteByProjectIdAndUserId(id, userId);
    }
}
