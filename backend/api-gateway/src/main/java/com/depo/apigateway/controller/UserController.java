package com.depo.apigateway.controller;

import com.depo.apigateway.dto.UserDto;
import com.depo.apigateway.dto.request.authority.UserAuthorityUpdateRequest;
import com.depo.apigateway.dto.request.role.UserRoleUpdateRequest;
import com.depo.apigateway.dto.request.user.UserCreateRequest;
import com.depo.apigateway.dto.request.user.UserUpdateRequest;
import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.codebase.CodebaseGetByIdResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
import com.depo.apigateway.dto.response.user.UserCreateResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import com.depo.apigateway.dto.response.user.UserGetByIdResponse;
import com.depo.apigateway.dto.response.user.UserUpdateResponse;
import com.depo.apigateway.entity.UserAuthority;
import com.depo.apigateway.entity.UserCodebase;
import com.depo.apigateway.entity.UserRole;
import com.depo.apigateway.mapper.AuthorityMapper;
import com.depo.apigateway.mapper.RoleMapper;
import com.depo.apigateway.mapper.UserAuthorityMapper;
import com.depo.apigateway.mapper.UserDtoMapper;
import com.depo.apigateway.mapper.UserRoleMapper;
import com.depo.apigateway.service.AuthorityService;
import com.depo.apigateway.service.CodebaseService;
import com.depo.apigateway.service.RoleService;
import com.depo.apigateway.service.UserAuthorityService;
import com.depo.apigateway.service.UserCodeBaseService;
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
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserAuthorityService userAuthorityService;
    private final RoleService roleService;
    private final AuthorityService authorityService;
    private final UserCodeBaseService userCodeBaseService;
    private final CodebaseService codebaseService;

    public UserController(UserService userService, UserRoleService userRoleService, UserAuthorityService userAuthorityService, @Qualifier("roleServiceImpl") RoleService roleService, @Qualifier("authorityServiceImpl") AuthorityService authorityService, UserCodeBaseService userCodeBaseService, CodebaseService codebaseService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.userAuthorityService = userAuthorityService;
        this.roleService = roleService;
        this.authorityService = authorityService;
        this.userCodeBaseService = userCodeBaseService;
        this.codebaseService = codebaseService;
    }

    @GetMapping
    public List<UserGetAllResponse> getAllUsers() {
        List<UserGetAllResponse> users = userService.getAllUsers();
        List<String> userIds = users.stream()
                .map(UserGetAllResponse::getId)
                .toList();
        List<UserRole> userRoles = userRoleService.getAllByUserIds(userIds);
        List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).toList();
        List<RoleGetAllResponse> roles = RoleMapper.INSTANCE.roleToGetAllResponse(roleService.getRolesByRoleIds(roleIds));

        for (UserRole userRole : userRoles) {
            users.stream().filter(u -> u.getId().equals(userRole.getUserId()))
                    .forEach(u -> u.getRoles().addAll(roles
                            .stream()
                            .filter(r -> r.getId().equals(userRole.getRoleId())).toList()));
        }
        return users;
    }

    @GetMapping("{id}")
    public UserGetByIdResponse getUserById(@PathVariable String id, @RequestParam @Nullable Boolean includeRoles, @RequestParam @Nullable Boolean includeAuthorities) {
        UserGetByIdResponse user = userService.getUserById(id);

        if (Objects.equals(includeRoles, true)) {
            List<UserRole> userRoles = userRoleService.getAllByUserId(id);
            List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).toList();
            List<RoleGetAllResponse> roles = RoleMapper.INSTANCE.roleToGetAllResponse(roleService.getRolesByRoleIds(roleIds));
            user.setRoles(roles);
        }
        if (Objects.equals(includeAuthorities, true)) {
            List<UserAuthority> userAuthorities = userAuthorityService.getAllByUserId(id);
            List<String> authorityIds = userAuthorities.stream().map(UserAuthority::getAuthorityId).toList();
            List<AuthorityGetAllResponse> authorities = AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAuthoritiesByAuthorityIds(authorityIds));
            user.setAuthorities(authorities);
        }
        return user;
    }

    @GetMapping("/username/{username}")
    public UserGetByIdResponse getUserByUsername(@PathVariable String username, @RequestParam @Nullable Boolean includeRoles, @RequestParam @Nullable Boolean includeAuthorities) {
        UserGetByIdResponse user = userService.getUserByUsername(username);

        if (Objects.equals(includeRoles, true)) {
            List<UserRole> userRoles = userRoleService.getAllByUserId(user.getId());
            List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).toList();
            List<RoleGetAllResponse> roles = RoleMapper.INSTANCE.roleToGetAllResponse(roleService.getRolesByRoleIds(roleIds));
            user.setRoles(roles);
        }
        if (Objects.equals(includeAuthorities, true)) {
            List<UserAuthority> userAuthorities = userAuthorityService.getAllByUserId(user.getId());
            List<String> authorityIds = userAuthorities.stream().map(UserAuthority::getAuthorityId).toList();
            List<AuthorityGetAllResponse> authorities = AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAuthoritiesByAuthorityIds(authorityIds));
            user.setAuthorities(authorities);
        }
        return user;
    }

    @PostMapping
    public UserCreateResponse createUser(@RequestBody UserCreateRequest request) {
        UserDto userDto = UserDtoMapper.INSTANCE.userCreateRequestToUser(request);
        return userService.createUser(userDto);
    }

    @PutMapping
    public UserUpdateResponse updateUser(@RequestBody UserUpdateRequest request) {
        UserDto userDto = UserDtoMapper.INSTANCE.userUpdateRequestToUser(request);
        return userService.updateUser(userDto);
    }

    @PutMapping("/roles")
    public void linkRolesToUsers(@RequestBody List<UserRoleUpdateRequest> request) {
        List<UserRole> userRoles = UserRoleMapper.INSTANCE.userRoleUpdateRequestToRole(request);
        userRoleService.updateAll(userRoles);
    }

    @PutMapping("/authorities")
    public void linkAuthoritiesToUsers(@RequestBody List<UserAuthorityUpdateRequest> request) {
        List<UserAuthority> userAuthorities = UserAuthorityMapper.INSTANCE.userAuthorityUpdateRequest(request);
        userAuthorityService.updateAll(userAuthorities);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        userRoleService.deleteAllByUserId(id);
        userAuthorityService.deleteAllByUserId(id);
    }

    @GetMapping("{id}/codebases")
    public List<CodebaseGetByIdResponse> getUserCodeBaseById(@PathVariable String id, @RequestParam Boolean isResponsible) {
        List<UserCodebase> userCodeBase = userCodeBaseService.getAllByUserIdAndIsResponsible(id, isResponsible);
        List<String> codebaseIds = userCodeBase.stream().map(UserCodebase::getCodebaseId).toList();
        List<CodebaseGetByIdResponse> codebases = codebaseIds.stream().map(codebaseService::getCodebaseById).toList();
        return codebases;
    }

    @DeleteMapping("{id}/roles/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleFromUser(@PathVariable String id, @PathVariable String roleId) {
        userRoleService.delete(roleId, id);
    }

    @DeleteMapping("{id}/authorities/{authorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorityFromUser(@PathVariable String id, @PathVariable String authorityId) {
        userAuthorityService.delete(id, authorityId);
    }
}
