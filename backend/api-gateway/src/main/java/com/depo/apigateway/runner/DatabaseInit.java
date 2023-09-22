package com.depo.apigateway.runner;

import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.UserAuthority;
import com.depo.apigateway.service.AuthorityService;
import com.depo.apigateway.service.RoleService;
import com.depo.apigateway.service.UserAuthorityService;
import com.depo.apigateway.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final UserAuthorityService userAuthorityService;
    private final UserRoleService userRoleService;

    private final AuthorityService authorityService;
    private final RoleService roleService;

    public DatabaseInit(UserAuthorityService userAuthorityService, UserRoleService userRoleService, AuthorityService authorityService, RoleService roleService) {
        this.userAuthorityService = userAuthorityService;
        this.userRoleService = userRoleService;
        this.authorityService = authorityService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //authorities.forEach(authorityService::createAuthority);
    }


//    public static final List<Authority> authorities = Arrays.asList(
//            new Authority(null,"BACKEND.GET",null,null,null),
//            new Authority(null,"BACKEND.POST",null,null,null),
//            new Authority(null,"BACKEND.PUT",null,null,null),
//            new Authority(null,"API.GET",null,null,null),
//            new Authority(null,"API.POST",null,null,null),
//            new Authority(null,"API.PUT",null,null,null)
//    );
}
