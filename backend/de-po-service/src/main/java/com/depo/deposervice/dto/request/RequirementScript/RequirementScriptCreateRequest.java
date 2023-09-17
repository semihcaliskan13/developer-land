package com.depo.deposervice.dto.request.RequirementScript;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementScriptCreateRequest {
    private String id;
    private String script;
}
