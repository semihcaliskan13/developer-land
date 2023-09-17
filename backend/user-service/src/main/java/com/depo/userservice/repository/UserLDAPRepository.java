package com.depo.userservice.repository;

import com.depo.userservice.entry.UserEntry;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLDAPRepository extends LdapRepository<UserEntry> {
    Optional<UserEntry> getByUsername(String username);
    Optional<UserEntry> getById(String id);

    @Query(base = "ou=users", value = "(objectClass=inetOrgPerson)", searchScope = SearchScope.ONELEVEL)
    List<UserEntry> getAllUsers();
}
