package com.depo.deposervice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class RequirementAccountUtil {

    public static String mapToString(Map<String, Object> genericAccountInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(genericAccountInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Map<String, Object> stringToMap(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
