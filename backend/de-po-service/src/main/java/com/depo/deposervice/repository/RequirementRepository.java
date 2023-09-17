package com.depo.deposervice.repository;

import com.depo.deposervice.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RequirementRepository extends JpaRepository<Requirement,String> {
}
