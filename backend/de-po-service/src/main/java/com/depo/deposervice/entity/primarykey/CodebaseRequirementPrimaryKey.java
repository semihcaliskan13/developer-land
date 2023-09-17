package com.depo.deposervice.entity.primarykey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodebaseRequirementPrimaryKey implements Serializable {
    private String codebaseId;
    private String requirementId;
    private String sourceType;
}
