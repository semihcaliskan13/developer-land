package com.depo.deposervice.service;

import com.depo.deposervice.entity.Project;
import java.util.List;

public interface ProjectService {

     Project getProjectById(String id);
     List<Project> getAllProjects();
     Project saveProject(Project project);
     Project updateProject(Project project);
     void deleteProject(String id);
}
