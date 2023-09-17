package com.depo.userservice.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(base = "ou=users", objectClasses = {"person", "inetOrgPerson", "top"})
@NoArgsConstructor
@AllArgsConstructor
@Data
public final class UserEntry {
    @Id
    private Name dn;

    @Attribute(name = "uid")
    private String id;

    @Attribute(name = "uid")
    private String username;

    @Attribute(name = "sn")
    private String lastName;

    @Attribute(name = "givenName")
    private String firstName;

    @Attribute(name = "displayName")
    private String displayName;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "mobile")
    private String phoneNumber;

    @Attribute(name = "userPassword", type = Attribute.Type.BINARY)
    private byte[] passwordByte;
}
