package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.UserCodebase;
import com.depo.apigateway.entry.UserCodebaseEntry;
import com.depo.apigateway.exception.CodebaseNotFoundException;
import com.depo.apigateway.exception.ResourceNotFoundException;
import com.depo.apigateway.repository.UserCodebaseLDAPRepository;
import com.depo.apigateway.service.UserCodeBaseService;
import com.depo.apigateway.util.StringUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("userCodebaseLDAPService")
@ConditionalOnProperty(name = "data.source.location", havingValue = "ldap")
public class UserCodebaseLDAPService implements UserCodeBaseService {
    private final UserCodebaseLDAPRepository userCodebaseLDAPRepository;
    private final StringUtil stringUtil;

    public UserCodebaseLDAPService(UserCodebaseLDAPRepository userCodebaseLDAPRepository, StringUtil stringUtil) {
        this.userCodebaseLDAPRepository = userCodebaseLDAPRepository;
        this.stringUtil = stringUtil;
    }


    @Override
    public List<UserCodebase> getAllByCodebaseId(String id) {
        UserCodebaseEntry entry = userCodebaseLDAPRepository.getCodebaseById(id);
        if (entry == null) throw new CodebaseNotFoundException();

        return convertUserCodebaseEntryToUserCodebase(entry);
    }

    @Override
    public UserCodebase create(UserCodebase userCodebase) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void updateAll(List<UserCodebase> userCodebases) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void deleteAll(String id) {
        throw new ResourceNotFoundException();
    }

    @Override
    public void delete(String id, String userId) {
        throw new ResourceNotFoundException();
    }

    private List<UserCodebase> convertUserCodebaseEntryToUserCodebase(UserCodebaseEntry userCodebaseEntry) {
        List<UserCodebase> userCodebases = userCodebaseEntry.getMembers().stream()
                .map(member -> {
                    if (!member.contains("uid")) return null;
                    String userId = member.split(",")[0].split("=")[1];
                    return new UserCodebase(userId, userCodebaseEntry.getId(), false);
                }).collect(Collectors.toList());

        List<String> responsibles = stringUtil.getAttributesByDescriptions(userCodebaseEntry.getDescription(), "responsible");
        if (responsibles.size() > 0) {
            for (String responsible : responsibles) {
                UserCodebase userCodebase = new UserCodebase(responsible, userCodebaseEntry.getId(), true);
                userCodebases.add(userCodebase);
            }
        }

        return userCodebases;
    }
}
