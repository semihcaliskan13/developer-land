package com.depo.deposervice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "codebase")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Codebase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "createdDate", nullable = true, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updatedDate",nullable = true)
    private LocalDateTime updatedDate;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", insertable=false, updatable=false)
    private Project project;

    @Column(name = "projectId")
    private String projectId;
}

