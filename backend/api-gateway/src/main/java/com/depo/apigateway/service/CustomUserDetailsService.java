package com.depo.apigateway.service;

import com.depo.apigateway.dto.CustomUserDetails;
import com.depo.apigateway.dto.UserCredential;
import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
import com.depo.apigateway.entity.Role;
import com.depo.apigateway.entity.UserAuthority;
import com.depo.apigateway.entity.UserRole;
import com.depo.apigateway.mapper.AuthorityMapper;
import com.depo.apigateway.mapper.RoleMapper;
import com.depo.apigateway.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;
    private final UserRoleService userRoleService;
    private final UserAuthorityService userAuthorityService;
    private final RoleService roleService;
    private final AuthorityService authorityService;

    public CustomUserDetailsService(UserService userService, HttpServletRequest request, JwtUtil jwtUtil, UserRoleService userRoleService, UserAuthorityService userAuthorityService, RoleService roleService, AuthorityService authorityService) {
        this.userService = userService;
        this.request = request;
        this.jwtUtil = jwtUtil;
        this.userRoleService = userRoleService;
        this.userAuthorityService = userAuthorityService;
        this.roleService = roleService;
        this.authorityService = authorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String token = jwtUtil.resolveToken(request);
            jwtUtil.tokenControl(token);
        }
        UserCredential credential = userService.getUserCredentialsByUsername(username);

        List<UserRole> userRoles = userRoleService.getAllByUserId(credential.getId());
        List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).toList();
        List<Role> roles = roleService.getRolesByRoleIds(roleIds);
        List<RoleGetAllResponse> rolesResponse = RoleMapper.INSTANCE.roleToGetAllResponse(roles);
        credential.setRoles(rolesResponse);

        List<UserAuthority> userAuthorities = userAuthorityService.getAllByUserId(credential.getId());
        List<String> authorityIds = userAuthorities.stream().map(UserAuthority::getAuthorityId).toList();
        List<AuthorityGetAllResponse> authorities = AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAuthoritiesByAuthorityIds(authorityIds));
        credential.setAuthorities(authorities);
        List<AuthorityGetAllResponse> authoritiesFromRole = AuthorityMapper.INSTANCE.authorityToGetAllResponse(authorityService.getAllAuthoritiesByRoles(roles));
        credential.getAuthorities().addAll(authoritiesFromRole);
        credential.setNonExpired(true);

        return new CustomUserDetails(credential);
    }
}
