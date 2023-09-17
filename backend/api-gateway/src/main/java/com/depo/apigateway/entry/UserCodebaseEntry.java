package com.depo.apigateway.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.List;

@Entry(base = "ou=groups", objectClasses = {"groupOfNames", "top"})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class UserCodebaseEntry {
    @Id
    private Name dn;

    @Attribute(name = "cn")
    private String id;

    @Attribute(name = "cn")
    private String name;

    @Attribute(name = "member")
    private List<String> members;

    @Attribute(name = "description")
    private List<String> description;
}
