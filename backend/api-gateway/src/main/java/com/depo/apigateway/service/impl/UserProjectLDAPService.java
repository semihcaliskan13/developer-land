package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.UserProject;
import com.depo.apigateway.entry.UserProjectEntry;
import com.depo.apigateway.exception.ProjectNotFoundException;
import com.depo.apigateway.exception.ResourceNotFoundException;
import com.depo.apigateway.mapper.UserProjectEntryMapper;
import com.depo.apigateway.repository.UserProjectLDAPRepository;
import com.depo.apigateway.service.UserProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("userProjectLDAPService")
@ConditionalOnProperty(name = "data.source.location", havingValue = "ldap")
public class UserProjectLDAPService implements UserProjectService {
    private final UserProjectLDAPRepository userProjectLDAPRepository;

    public UserProjectLDAPService(UserProjectLDAPRepository userProjectLDAPRepository) {
        this.userProjectLDAPRepository = userProjectLDAPRepository;
    }

    @Override
    public List<UserProject> findAllByProjectId(String projectId) {
        UserProjectEntry userProjectEntry = userProjectLDAPRepository.getByProjectId(projectId).orElseThrow(ProjectNotFoundException::new);
        return UserProjectEntryMapper.convertUserProjectEntryToUserProject(userProjectEntry);
    }

    @Override
    public List<UserProject> findAllByProjectId(List<String> projectIds) {
        String ids = projectIds.stream().map(id -> "(ou=" + id + ")").reduce(" ", String::concat);
        LdapQuery query = LdapQueryBuilder.query()
                .base("ou=nprojects,ou=other")
                .filter("(|" + ids + ")");
        List<UserProjectEntry> userProjectEntries = userProjectLDAPRepository.findAll(query);

        return userProjectEntries.stream()
                .map(UserProjectEntryMapper::convertUserProjectEntryToUserProject)
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public UserProject create(UserProject userProject) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteAll(String id) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void updateAll(List<UserProject> userProjects) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void delete(String id, String userId) {
        throw new ResourceNotFoundException();
    }

}
