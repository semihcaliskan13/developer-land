package com.depo.deposervice.service.impl;

import com.depo.deposervice.entity.Project;
import com.depo.deposervice.entry.ProjectEntry;
import com.depo.deposervice.exception.ProjectNotFoundException;
import com.depo.deposervice.exception.ResourceNotFoundException;
import com.depo.deposervice.mapper.ProjectMapper;
import com.depo.deposervice.repository.ProjectLDAPRepository;
import com.depo.deposervice.service.ProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("projectLDAPService")
@ConditionalOnProperty(name = "data.source.location", havingValue = "ldap")
public class ProjectLDAPService implements ProjectService {
    private final ProjectLDAPRepository projectLDAPRepository;

    public ProjectLDAPService(ProjectLDAPRepository projectLDAPRepository) {
        this.projectLDAPRepository = projectLDAPRepository;
    }

    @Override
    public Project getProjectById(String id) {
        ProjectEntry projectEntry = projectLDAPRepository.getById(id).orElseThrow(ProjectNotFoundException::new);
        return ProjectMapper.INSTANCE.projectEntryToProject(projectEntry);
    }

    @Override
    public List<Project> getAllProjects() {
        return ProjectMapper.INSTANCE.projectEntriesToProjects(projectLDAPRepository.getAllProjects());
    }

    @Override
    public Project saveProject(Project project) {
        throw new ResourceNotFoundException();
    }

    @Override
    public Project updateProject(Project project) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteProject(String id) {
        throw new ResourceNotFoundException();
    }

    public Project getProjectByName(String name) {
        ProjectEntry projectEntry = projectLDAPRepository.getProjectByName(name);
        return ProjectMapper.INSTANCE.projectEntryToProject(projectEntry);
    }
}
