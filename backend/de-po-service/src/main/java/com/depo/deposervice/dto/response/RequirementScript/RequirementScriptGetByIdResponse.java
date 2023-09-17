package com.depo.deposervice.dto.response.RequirementScript;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementScriptGetByIdResponse {
    private String id;
    private String script;
}
