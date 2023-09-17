package com.depo.apigateway.service;

import com.depo.apigateway.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRoleById(String id);

    List<Role> getRolesByAuthorityId(String authorityId);

    List<Role> getRolesByRoleIds(List<String> ids);

    Role createRole(Role role);

    Role updateRole(Role role);

    void linkAuthorityToRole(String roleId, List<String> authorityIds);

    void deleteRole(String id);

    void deleteAuthorityFromRole(String roleId, String authorityId);


}
