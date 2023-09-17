package com.depo.apigateway.dto.response.RequirementAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementAccountGetByIdResponse {
    private String id;
    private String description;
    private Map<String,Object> genericAccountInfo;
}
