package com.depo.deposervice.service.impl;

import com.depo.deposervice.entity.Codebase;
import com.depo.deposervice.entity.Project;
import com.depo.deposervice.entry.CodebaseEntry;
import com.depo.deposervice.exception.ResourceNotFoundException;
import com.depo.deposervice.mapper.CodebaseMapper;
import com.depo.deposervice.repository.CodebaseLDAPRepository;
import com.depo.deposervice.service.CodebaseService;
import com.depo.deposervice.service.ProjectService;
import com.depo.deposervice.util.StringUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("codebaseLDAPService")
@ConditionalOnProperty(name = "data.source.location", havingValue = "ldap")
public class CodebaseLDAPService implements CodebaseService {

    private final CodebaseLDAPRepository codebaseLDAPRepository;
    private final ProjectLDAPService projectService;
    private final StringUtil stringUtil;

    public CodebaseLDAPService(CodebaseLDAPRepository codebaseLDAPRepository, ProjectLDAPService projectService, StringUtil stringUtil) {
        this.codebaseLDAPRepository = codebaseLDAPRepository;
        this.projectService = projectService;
        this.stringUtil = stringUtil;
    }


    @Override
    public Codebase getCodeBaseById(String id) {
        CodebaseEntry codebaseEntry = codebaseLDAPRepository.getCodebaseById(id);
        return getCodebase(codebaseEntry);
    }

    @Override
    public List<Codebase> getAllCodeBases() {
        List<CodebaseEntry> codebaseEntries = codebaseLDAPRepository.getAllCodebases();
        return codebaseEntries.stream()
                .map(codebaseEntry -> getCodebase(codebaseEntry))
                .toList();
    }

    @Override
    public List<Codebase> getAllCodebasesByIds(List<String> codebaseIds) {
        return codebaseIds.stream().map(this::getCodeBaseById).toList();
    }

    @Override
    public Codebase saveCodeBase(Codebase codeBase) {
        throw new ResourceNotFoundException();
    }

    @Override
    public Codebase updateCodeBase(Codebase codeBase) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteCodeBase(String id) {
        throw new ResourceNotFoundException();
    }

    @Override
    public List<Codebase> getCodeBasesByProjectId(String id) {
        List<CodebaseEntry> codebaseEntries = codebaseLDAPRepository.getCodebasesByProjectId(id);
        return codebaseEntries.stream()
                .map(codebaseEntry -> getCodebase(codebaseEntry))
                .toList();
    }


    private Codebase getCodebase(CodebaseEntry codebaseEntry) {
        Codebase codebase = CodebaseMapper.INSTANCE.codebaseEntryToCodebase(codebaseEntry);
        String projectId = stringUtil.getAttributeByDescriptions(codebaseEntry.getDescription(), "project");
        codebase.setName(codebase.getName().replace("svn_", ""));

        if (projectId != null) {
            Project project = projectService.getProjectById(projectId);
            if (project != null) {
                codebase.setProject(project);
                return codebase;
            }
        }

        String projectName = stringUtil.getProjectNameByCodebaseName(codebase.getName());
        Project project = projectService.getProjectByName(projectName);
        if (project != null)
            codebase.setProject(project);
        return codebase;
    }
}
