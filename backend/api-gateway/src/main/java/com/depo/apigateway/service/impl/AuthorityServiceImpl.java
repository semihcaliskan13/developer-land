package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.Role;
import com.depo.apigateway.exception.AuthorityNotFoundException;
import com.depo.apigateway.repository.AuthorityRepository;
import com.depo.apigateway.service.AuthorityService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("authorityServiceImpl")
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority getAuthorityById(String id) {
        return authorityRepository.findById(id).orElseThrow(AuthorityNotFoundException::new);
    }

    @Override
    public List<Authority> getAuthoritiesByCodebaseName(String codebaseName) {
        return authorityRepository.getAuthoritiesByAuthorityStartsWith(codebaseName.toUpperCase() + ".");
    }

    @Override
    public List<Authority> getAuthoritiesByRoleId(String roleId) {
        Role role = new Role();
        role.setId(roleId);
        return authorityRepository.getAuthoritiesByRoles(role);
    }

    @Override
    public List<Authority> getAllAuthoritiesByRoles(List<Role> roles) {
        return authorityRepository.findAllByRolesIn(roles);
    }

    @Override
    public List<Authority> getAuthoritiesByAuthorityIds(List<String> authorityIds) {
        return authorityRepository.findAllById(authorityIds);
    }

    @Override
    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority updateAuthority(Authority authority) {
        if (authorityRepository.existsById(authority.getId())) {
            authorityRepository.save(authority);
        }
        throw new AuthorityNotFoundException();
    }

    @Override
    public void deleteAuthority(String id) {
        authorityRepository.deleteById(id);
    }

}
