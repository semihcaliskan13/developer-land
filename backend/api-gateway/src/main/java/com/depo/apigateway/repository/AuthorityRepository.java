package com.depo.apigateway.repository;

import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    List<Authority> getAuthoritiesByRoles(Role role);
    List<Authority> findAllByRolesIn(List<Role> roles);
    List<Authority> getAuthoritiesByAuthorityStartsWith(String codebaseName);
}
