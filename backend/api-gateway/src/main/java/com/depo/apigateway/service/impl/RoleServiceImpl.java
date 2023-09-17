package com.depo.apigateway.service.impl;

import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.Role;
import com.depo.apigateway.exception.RoleNotFoundException;
import com.depo.apigateway.repository.RoleRepository;
import com.depo.apigateway.service.AuthorityService;
import com.depo.apigateway.service.RoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final AuthorityService authorityService;

    public RoleServiceImpl(RoleRepository roleRepository, @Qualifier("authorityServiceImpl") AuthorityService authorityService) {
        this.roleRepository = roleRepository;
        this.authorityService = authorityService;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public List<Role> getRolesByAuthorityId(String authorityId) {
        Authority authority = new Authority();
        authority.setId(authorityId);
        return roleRepository.getRolesByAuthorities(authority);
    }

    @Override
    public List<Role> getRolesByRoleIds(List<String> ids) {
        return roleRepository.findAllById(ids);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        if (roleRepository.existsById(role.getId())) {
            return roleRepository.save(role);

        }
        throw new RoleNotFoundException();
    }

    @Override
    public void linkAuthorityToRole(String roleId, List<String> authorityIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(RoleNotFoundException::new);
        List<Authority> authorities = authorityService.getAuthoritiesByAuthorityIds(authorityIds);
        authorities.forEach(authority -> {
            if (!role.getAuthorities().contains(authority)) {
                role.getAuthorities().add(authority);
            }
        });
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAuthorityFromRole(String roleId, String authorityId) {
        Role role = roleRepository.findById(roleId).orElseThrow(RoleNotFoundException::new);
        Authority authority = authorityService.getAuthorityById(authorityId);
        role.getAuthorities().remove(authority);
        roleRepository.save(role);
    }
}
