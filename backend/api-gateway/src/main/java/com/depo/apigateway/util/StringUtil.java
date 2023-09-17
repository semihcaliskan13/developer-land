package com.depo.apigateway.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringUtil {


    public String getAttributeByDescriptions(List<String> descriptions, String attribute) {

        for (String description : descriptions) {
            String[] parts = description.split(": ");
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1];
                if (attribute.equals(key)) {
                    return value;
                }
            }
        }

        return null;
    }

    public List<String> getAttributesByDescriptions(List<String> descriptions, String attribute) {
        List<String> values = new ArrayList<>();

        for (String description : descriptions) {
            String[] parts = description.split(": ");
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1];
                if (attribute.equals(key)) {
                    values.add(value);
                }
            }
        }

        return values.size() > 0 ? values : null;
    }

}
