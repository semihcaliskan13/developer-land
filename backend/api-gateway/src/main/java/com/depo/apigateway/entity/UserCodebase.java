package com.depo.apigateway.entity;

import com.depo.apigateway.entity.primarykey.UserCodebasePrimaryKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_codebase")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(UserCodebasePrimaryKey.class)
public class UserCodebase {

    @Id
    @Column(name = "userId", nullable = false)
    private String userId;

    @Id
    @Column(name = "codebaseId")
    private String codebaseId;

    @Column(name = "responsible")
    private boolean responsible;
}
