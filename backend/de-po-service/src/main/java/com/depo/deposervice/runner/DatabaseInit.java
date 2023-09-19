package com.depo.deposervice.runner;

import com.depo.deposervice.entity.Codebase;
import com.depo.deposervice.entity.CodebaseRequirement;
import com.depo.deposervice.entity.Project;
import com.depo.deposervice.entity.Requirement;
import com.depo.deposervice.service.CodebaseRequirementService;
import com.depo.deposervice.service.CodebaseService;
import com.depo.deposervice.service.ProjectService;
import com.depo.deposervice.service.RequirementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final ProjectService projectService;
    private final CodebaseService codebaseService;
    private final RequirementService requirementService;
    private final CodebaseRequirementService codebaseRequirementService;

    public DatabaseInit(ProjectService projectService, CodebaseService codebaseService, RequirementService requirementService, CodebaseRequirementService codebaseRequirementService) {
        this.projectService = projectService;
        this.codebaseService = codebaseService;
        this.requirementService = requirementService;
        this.codebaseRequirementService = codebaseRequirementService;
    }

    @Override
    public void run(String... args) throws Exception {
//        for (int i = 0; i< (long) PROJECTS.size(); i++) {
//            Project project = projectService.saveProject(PROJECTS.get(i));
//            Codebase codebase = CODEBASES.get(i);
//            codebase.setProjectId(project.getId());
//            codebaseService.saveCodeBase(codebase);
//
//        }
//        CODEBASES.forEach(codebaseService::saveCodeBase);
//        REQUIREMENTS.forEach(requirementService::save);
//        CODEBASE_REQUIREMENTS.forEach(codebaseRequirementService::save);
    }


    public static final List<Project> PROJECTS = Arrays.asList(
            new Project("1", "Apache Hadoop", null, null, null),
            new Project("2", "Django", null, null, null),
            new Project("3", "React.js", null, null, null),
            new Project("4", "Spring Boot", null, null, null),
            new Project("5", "Angular", null, null, null),
            new Project("6", "TensorFlow", null, null, null),
            new Project("7", "Node.js", null, null, null),
            new Project("8", "Ruby on Rails", null, null, null),
            new Project("9", "Eclipse IDE", null, null, null),
            new Project("10", "Visual Studio Code", null, null, null)

    );

    public static final List<Codebase> CODEBASES = Arrays.asList(
            new Codebase("1", "Backend", "Backend codebase", null, null, null, "1"),
            new Codebase("2", "Frontend", "Frontend codebase", null, null, null, "2"),
            new Codebase("3", "Util", "Utility codebase", null, null, null, "3"),
            new Codebase("4", "Database", "Database scripts", null, null, null, "4"),
            new Codebase("5", "API", "API codebase", null, null, null, "5"),
            new Codebase("6", "Mobile", "Mobile app codebase", null, null, null, "6"),
            new Codebase("7", "Testing", "Testing scripts", null, null, null, "7"),
            new Codebase("8", "DevOps", "DevOps scripts", null, null, null, "8"),
            new Codebase("9", "Analytics", "Analytics codebase", null, null, null, "9"),
            new Codebase("10", "Security", "Security codebase", null, null, null, "10")
    );

    public static final List<Requirement> REQUIREMENTS = Arrays.asList(
            new Requirement("1", "Docker", "2.0", null, null),
            new Requirement("2", "MySQL", "8.0", null, null),
            new Requirement("3", "Elasticsearch", "7.0", null, null),
            new Requirement("4", "Kubernetes", "1.20", null, null),
            new Requirement("5", "Java", "11", null, null),
            new Requirement("6", "Spring Boot", "2.5", null, null),
            new Requirement("7", "Node.js", "14", null, null),
            new Requirement("8", "React.js", "17", null, null),
            new Requirement("9", "Python", "3.9", null, null),
            new Requirement("10", "Angular", "12", null, null)
    );

    public static final List<CodebaseRequirement> CODEBASE_REQUIREMENTS = Arrays.asList(
            new CodebaseRequirement("1", "1", "db"),
            new CodebaseRequirement("1", "2", "db"),
            new CodebaseRequirement("1", "3", "db"),
            new CodebaseRequirement("1", "4", "db"),
            new CodebaseRequirement("1", "5", "db"),
            new CodebaseRequirement("1", "6", "db"),
            new CodebaseRequirement("1", "7", "db"),
            new CodebaseRequirement("1", "8", "db"),
            new CodebaseRequirement("1", "9", "db"),
            new CodebaseRequirement("1", "10", "db")
    );


}
