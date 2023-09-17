package com.depo.deposervice.service.impl;


import com.depo.deposervice.entity.Project;
import com.depo.deposervice.exception.ProjectNotFoundException;
import com.depo.deposervice.repository.ProjectRepository;
import com.depo.deposervice.service.ProjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Qualifier("projectServiceImpl")
@ConditionalOnProperty(name = "data.source.location", havingValue = "db")
public class ProjectServiceImpl implements ProjectService {
    public final ProjectRepository projectRepository;

    public ProjectServiceImpl( ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        if (projectRepository.existsById(project.getId())){
            return projectRepository.save(project);
        }
        throw new ProjectNotFoundException();
    }

    @Override
    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}




