package com.depo.apigateway.repository;

import com.depo.apigateway.entity.Authority;
import com.depo.apigateway.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> getRolesByAuthorities(Authority authority);

}
