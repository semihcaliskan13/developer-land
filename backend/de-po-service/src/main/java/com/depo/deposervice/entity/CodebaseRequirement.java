package com.depo.deposervice.entity;

import com.depo.deposervice.entity.primarykey.CodebaseRequirementPrimaryKey;
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
@Table(name = "codebase_requirement")
@IdClass(CodebaseRequirementPrimaryKey.class)
public class CodebaseRequirement {
    @Id
    @Column(name = "codebaseId")
    private String codebaseId;

    @Id
    @Column(name = "requirementId")
    private String requirementId;

    @Id
    @Column(name = "sourceType")
    private String sourceType;
}
