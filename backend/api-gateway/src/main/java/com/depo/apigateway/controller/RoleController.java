package com.depo.apigateway.controller;


import com.depo.apigateway.dto.request.role.RoleAuthorityUpdateRequest;
import com.depo.apigateway.dto.request.role.RoleCreateRequest;
import com.depo.apigateway.dto.request.role.RoleUpdateRequest;
import com.depo.apigateway.dto.request.role.UserRoleUpdateRequest;
import com.depo.apigateway.dto.response.role.RoleCreateResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
import com.depo.apigateway.dto.response.role.RoleGetByIdResponse;
import com.depo.apigateway.dto.response.role.RoleUpdateResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import com.depo.apigateway.entity.Role;
import com.depo.apigateway.entity.UserRole;
import com.depo.apigateway.mapper.AuthorityMapper;
import com.depo.apigateway.mapper.RoleMapper;
import com.depo.apigateway.mapper.UserRoleMapper;
import com.depo.apigateway.service.AuthorityService;
import com.depo.apigateway.service.RoleService;
import com.depo.apigateway.service.UserRoleService;
import com.depo.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final AuthorityService authorityService;

    public RoleController(@Qualifier("roleServiceImpl") RoleService roleService, UserRoleService userRoleService, UserService userService, @Qualifier("authorityServiceImpl") AuthorityService authorityService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public List<RoleGetAllResponse> getRoles() {
        return RoleMapper.INSTANCE.roleToGetAllResponse(roleService.getRoles());
    }

    @GetMapping("{id}")
    public RoleGetByIdResponse getRoleById(@PathVariable String id, @RequestParam @Nullable Boolean includeUsers, @RequestParam @Nullable Boolean includeAuthorities) {
        Role roleResponse;
        roleResponse = roleService.getRoleById(id);
        RoleGetByIdResponse role = RoleMapper.INSTANCE.roleToGetByIdResponse(roleResponse);

        if (Objects.equals(includeUsers, true)) {
            List<UserRole> userRoles = userRoleService.getAllByRoleId(id);
            List<String> userIds = userRoles.stream().map(UserRole::getUserId).toList();
            List<UserGetAllResponse> users = userService.getUsersById(userIds);
            role.setUsers(users);
            return role;
        }
        if (Objects.equals(includeAuthorities, true)) {
            role.setAuthorities(AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAuthoritiesByRoleId(id)));
        }
        return role;
    }

    @PostMapping
    public RoleCreateResponse createRole(@RequestBody RoleCreateRequest request) {
        Role role = RoleMapper.INSTANCE.roleCreateRequestToRole(request);
        return RoleMapper.INSTANCE.roleToCreateResponse(roleService.createRole(role));
    }

    @PutMapping
    public RoleUpdateResponse updateRole(@RequestBody RoleUpdateRequest request) {
        Role role = RoleMapper.INSTANCE.roleUpdateRequestToRole(request);
        return RoleMapper.INSTANCE.roleToUpdateResponse(roleService.updateRole(roleService.updateRole(role)));
    }

    @PutMapping("/users")
    public void linkUsersToRole(@RequestBody List<UserRoleUpdateRequest> request) {
        List<UserRole> userRoles = UserRoleMapper.INSTANCE.userRoleUpdateRequestToRole(request);
        userRoleService.updateAll(userRoles);
    }

    @PutMapping("/{id}/authorities")
    public void linkAuthoritiesToRole(@PathVariable String id, @RequestBody RoleAuthorityUpdateRequest request) {
        roleService.linkAuthorityToRole(id, request.getAuthorityIds());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        userRoleService.deleteAllByRoleId(id);
    }

    @DeleteMapping("{id}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserRole(@PathVariable String id, @PathVariable String userId) {
        userRoleService.delete(id, userId);
    }

    @DeleteMapping("{id}/authorities/{authorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorityFromRole(@PathVariable String id, @PathVariable String authorityId) {
        roleService.deleteAuthorityFromRole(id, authorityId);
    }
}
