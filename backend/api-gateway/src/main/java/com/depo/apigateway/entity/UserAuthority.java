package com.depo.apigateway.entity;


import com.depo.apigateway.entity.primarykey.UserAuthorityPrimaryKey;
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
@Table(name = "user_authority")
@IdClass(UserAuthorityPrimaryKey.class)
public class UserAuthority {
    @Id
    @Column(name = "userId")
    private String userId;

    @Id
    @Column(name = "authorityId")
    private String authorityId;
}
