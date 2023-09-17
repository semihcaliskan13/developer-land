package com.depo.deposervice.repository;

import com.depo.deposervice.entity.Codebase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CodebaseRepository extends JpaRepository<Codebase, String> {
     List<Codebase> getCodeBaseByProjectId(String id);
}
