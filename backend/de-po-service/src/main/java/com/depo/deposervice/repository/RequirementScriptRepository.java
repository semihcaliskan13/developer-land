package com.depo.deposervice.repository;

import com.depo.deposervice.collection.RequirementScript;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementScriptRepository extends MongoRepository<RequirementScript, String> {
}
