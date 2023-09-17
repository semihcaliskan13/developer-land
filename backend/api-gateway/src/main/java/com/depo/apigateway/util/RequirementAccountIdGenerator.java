package com.depo.apigateway.util;


public class RequirementAccountIdGenerator {

    public static String generateUserAccountId(String userId, String requirementId) {
        return userId + "_" + requirementId;
    }
}
