package com.depo.apigateway.repository;

import com.depo.apigateway.entry.UserCodebaseEntry;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodebaseLDAPRepository extends LdapRepository<UserCodebaseEntry> {
    @Query(base = "ou=groups", value = "(&(objectClass=groupOfNames)(cn={0}))")
    UserCodebaseEntry getCodebaseById(String id);
}
