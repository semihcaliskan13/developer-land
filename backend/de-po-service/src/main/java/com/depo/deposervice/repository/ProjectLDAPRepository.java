package com.depo.deposervice.repository;

import com.depo.deposervice.entry.ProjectEntry;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectLDAPRepository extends LdapRepository<ProjectEntry> {

    @Query(base = "ou=nprojects,ou=other",
            value = "(& (description=status: active) )",
            searchScope = SearchScope.ONELEVEL)
    List<ProjectEntry> getAllProjects();

    @Query(base = "ou=nprojects,ou=other",
            value = "(& (ou={0}) )",
            searchScope = SearchScope.ONELEVEL)
    Optional<ProjectEntry> getById(String id);

    @Query( base = "ou=nprojects,ou=other",
            value = "(& (ou=*.{0}) )",
            searchScope = SearchScope.ONELEVEL)
    ProjectEntry getProjectByName(String name);

}
