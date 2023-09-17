package com.depo.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementAccountDto {
    private String id;
    private String description;
    private Map<String,Object> genericAccountInfo;
}
