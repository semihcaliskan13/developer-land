package com.depo.deposervice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "requirement",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "version"})})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "version")
    private String version;

    @CreationTimestamp
    @Column(name = "createdDate", updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;
}
