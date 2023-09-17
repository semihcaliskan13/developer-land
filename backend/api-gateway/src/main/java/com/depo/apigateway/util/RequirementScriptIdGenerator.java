package com.depo.apigateway.util;

public class RequirementScriptIdGenerator {
    public static String generateRequirementScriptId(String codebaseId, String requirementId) {
        return codebaseId + "_" + requirementId;
    }
}
