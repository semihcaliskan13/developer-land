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
public class UserAuthorityPrimaryKey implements Serializable {
    private String userId;
    private String authorityId;
}
