package com.depo.apigateway.mapper;

import com.depo.apigateway.entity.UserProject;
import com.depo.apigateway.entry.UserProjectEntry;

import java.util.List;
import java.util.Objects;

public class UserProjectEntryMapper {

    public static List<UserProject> convertUserProjectEntryToUserProject(UserProjectEntry projectEntry) {
        List<UserProject> userProjects = projectEntry.getDescriptions().stream()
                .map(description -> {
                    String[] parts = description.split(": ");
                    String key = parts[0];
                    String value = parts[1];
                    if (!key.contains("projectResponsible")) return null;
                    return new UserProject(value.trim(), projectEntry.getProjectId());
                }).toList();
        return userProjects.stream().filter(Objects::nonNull).toList();
    }
}
