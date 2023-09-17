package com.depo.deposervice.util;

import org.springframework.stereotype.Component;

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

    public String getProjectNameByCodebaseName(String codebaseName) {
        String input = codebaseName.replace("svn_", "");
        int index = input.indexOf("-");
        if (index != -1)
            return input.substring(0, index);

        return input;
    }

}
