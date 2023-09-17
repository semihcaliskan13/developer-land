package com.depo.deposervice.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "requirement_account")
public class RequirementAccount {

    @Id
    private String id;
    private String description;
    private String genericAccountInfo;
}
