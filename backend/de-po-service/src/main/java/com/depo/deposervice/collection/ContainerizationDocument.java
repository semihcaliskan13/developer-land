package com.depo.deposervice.collection;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "containerization_document")
public class ContainerizationDocument {

    @Id
    private String id;
    private String name;
    private String requirementName;
    private String requirementVersion;
    private byte[] file;

}
