package com.depo.apigateway.repository;

import com.depo.apigateway.entry.UserProjectEntry;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProjectLDAPRepository extends LdapRepository<UserProjectEntry> {
    Optional<UserProjectEntry> getByProjectId(String projectId);
}
