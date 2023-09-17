package com.depo.apigateway.entity;

import com.depo.apigateway.entity.primarykey.UserRolePrimaryKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_role")
@IdClass(UserRolePrimaryKey.class)
public class UserRole {
    @Id
    @Column(name = "userId")
    private String userId;

    @Id
    @Column(name = "roleId")
    private String roleId;
}
