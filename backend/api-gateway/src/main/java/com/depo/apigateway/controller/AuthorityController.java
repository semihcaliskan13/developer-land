package com.depo.apigateway.controller;

import com.depo.apigateway.dto.request.authority.AuthorityCreateRequest;
import com.depo.apigateway.dto.request.authority.AuthorityUpdateRequest;
import com.depo.apigateway.dto.request.authority.UserAuthorityUpdateRequest;
import com.depo.apigateway.dto.response.authority.AuthorityCreateResponse;
import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.authority.AuthorityGetByIdResponse;
import com.depo.apigateway.dto.response.authority.AuthorityUpdateResponse;
import com.depo.apigateway.dto.response.user.UserGetAllResponse;
import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.UserAuthority;
import com.depo.apigateway.mapper.AuthorityMapper;
import com.depo.apigateway.mapper.RoleMapper;
import com.depo.apigateway.mapper.UserAuthorityMapper;
import com.depo.apigateway.service.AuthorityService;
import com.depo.apigateway.service.RoleService;
import com.depo.apigateway.service.UserAuthorityService;
import com.depo.apigateway.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/v1/authorities")
@SecurityRequirement(name="bearerAuth")
public class AuthorityController {

    private final AuthorityService authorityService;
    private final RoleService roleService;
    private final UserAuthorityService userAuthorityService;
    private final UserService userService;

    public AuthorityController(@Qualifier("authorityServiceImpl") AuthorityService authorityService, @Qualifier("roleServiceImpl") RoleService roleService, UserAuthorityService userAuthorityService, UserService userService) {
        this.authorityService = authorityService;
        this.roleService = roleService;
        this.userAuthorityService = userAuthorityService;
        this.userService = userService;
    }

    @GetMapping
    public List<AuthorityGetAllResponse> getAuthorities() {
        return AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAuthorities());
    }

    @GetMapping("{id}")
    public AuthorityGetByIdResponse getAuthorityById(@PathVariable String id, @RequestParam @Nullable Boolean includeUsers, @RequestParam @Nullable Boolean includeRoles) {
        AuthorityGetByIdResponse authority = AuthorityMapper.INSTANCE.authorityToGetByIdResponse(authorityService.getAuthorityById(id));
        if (Objects.equals(includeUsers, true)) {
            List<UserAuthority> userAuthorities = userAuthorityService.getAllByAuthorityId(id);
            List<String> userIds = userAuthorities.stream().map(UserAuthority::getUserId).toList();
            List<UserGetAllResponse> users = userService.getUsersById(userIds);
            authority.setUsers(users);
        }
        if (Objects.equals(includeRoles, true)) {
            roleService.getRolesByAuthorityId(id);
            authority.setRoles(RoleMapper.INSTANCE.roleToGetAllResponse(roleService.getRolesByAuthorityId(id)));
        }
        return authority;
    }

    @PostMapping
    public AuthorityCreateResponse createAuthority(@RequestBody AuthorityCreateRequest request) {
        Authority authority = AuthorityMapper.INSTANCE.authorityCreateRequestToAuthority(request);
        return AuthorityMapper.INSTANCE.authorityToCreateResponse(authorityService.createAuthority(authority));
    }

    @PutMapping
    public AuthorityUpdateResponse updateAuthority(@RequestBody AuthorityUpdateRequest request) {
        Authority authority = AuthorityMapper.INSTANCE.authorityUpdateRequestToAuthority(request);
        return AuthorityMapper.INSTANCE.authorityToUpdateResponse(authorityService.updateAuthority(authority));
    }

    @PutMapping("/users")
    public void linkUserToAuthority(@RequestBody List<UserAuthorityUpdateRequest> request) {
        List<UserAuthority> authorities = UserAuthorityMapper.INSTANCE.userAuthorityUpdateRequest(request);
        userAuthorityService.updateAll(authorities);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthority(@PathVariable String id) {
        authorityService.deleteAuthority(id);
        userAuthorityService.deleteAllByAuthorityId(id);
    }

    @DeleteMapping("{id}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserFromAuthority(@PathVariable String id, @PathVariable String userId) {
        userAuthorityService.delete(userId, id);
    }
}
