package com.depo.apigateway.dto.request.RequirementScript;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementScriptExecuteRequest {
    private Map<String, String> placeholders;
}
