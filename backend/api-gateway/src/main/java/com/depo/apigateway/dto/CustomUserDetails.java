package com.depo.apigateway.dto;

import com.depo.apigateway.dto.response.authority.AuthorityGetAllResponse;
import com.depo.apigateway.dto.response.role.RoleGetAllResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String userId;
    private boolean nonExpired;
    private List<RoleGetAllResponse> roles;
    private List<AuthorityGetAllResponse> authorities;

    public CustomUserDetails(UserCredential userCredential) {
        this.username = userCredential.getUsername();
        this.password = userCredential.getPassword();
        this.userId = userCredential.getId();
        this.nonExpired = userCredential.isNonExpired();
        this.roles = userCredential.getRoles();
        this.authorities = userCredential.getAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> combinedAuthorities = new ArrayList<>(roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .toList());

        combinedAuthorities.addAll(authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .toList());

        return combinedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return nonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
