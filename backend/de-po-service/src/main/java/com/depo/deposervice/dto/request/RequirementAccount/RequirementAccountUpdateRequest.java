package com.depo.deposervice.dto.request.RequirementAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementAccountUpdateRequest {
    private String id;
    private String description;
    private Map<String,Object> genericAccountInfo;
}
