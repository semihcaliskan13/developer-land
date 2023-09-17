package com.depo.deposervice.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.List;

@Entry(base = "ou=nprojects,ou=other", objectClasses = {"top", "organizationalUnit"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ProjectEntry {
    @Id
    private Name dn;

    @Attribute(name = "ou")
    private String id;

    @Attribute(name = "ou")
    private String name;

    @Attribute(name = "description")
    private List<String> descriptions;

}
