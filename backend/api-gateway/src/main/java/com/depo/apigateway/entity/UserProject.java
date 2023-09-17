package com.depo.apigateway.entity;


import com.depo.apigateway.entity.primarykey.UserProjectPrimaryKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_project",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "projectId"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(UserProjectPrimaryKey.class)
public class UserProject {

    @Id
    @Column(name = "userId", nullable = false)
    private String userId;

    @Id
    @Column(name = "projectId")
    private String projectId;
}
