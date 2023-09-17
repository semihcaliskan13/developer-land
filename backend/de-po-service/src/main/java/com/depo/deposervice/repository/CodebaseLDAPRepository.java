package com.depo.deposervice.repository;

import com.depo.deposervice.entry.CodebaseEntry;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CodebaseLDAPRepository extends LdapRepository<CodebaseEntry> {

    @Query(base = "ou=groups", value = "(&(objectClass=groupOfNames)(description=status: active)(cn=svn_*))")
    List<CodebaseEntry> getAllCodebases();

    @Query(base = "ou=groups", value = "(&(objectClass=groupOfNames)(cn={0}))")
    CodebaseEntry getCodebaseById(String id);

    @Query(base = "ou=groups", value = "(&(cn=svn_{0}*))")
    List<CodebaseEntry> getCodebasesByProjectId(String projectId);
}
