package com.depo.apigateway.service;

import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.Role;

import java.util.List;

public interface AuthorityService {

    List<Authority> getAuthorities();

    Authority getAuthorityById(String id);

    List<Authority> getAuthoritiesByCodebaseName(String codebaseName);

    List<Authority> getAuthoritiesByRoleId(String roleId);
    List<Authority> getAllAuthoritiesByRoles(List<Role> roles);

    List<Authority> getAuthoritiesByAuthorityIds(List<String> authorityIds);

    Authority createAuthority(Authority authority);

    Authority updateAuthority(Authority authority);

    void deleteAuthority(String id);

}
