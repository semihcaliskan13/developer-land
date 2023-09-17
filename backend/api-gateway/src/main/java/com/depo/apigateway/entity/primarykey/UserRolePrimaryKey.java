package com.depo.apigateway.entity.primarykey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRolePrimaryKey implements Serializable {
    private String userId;
    private String roleId;
}
