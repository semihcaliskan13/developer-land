package com.depo.deposervice.repository;

import com.depo.deposervice.collection.RequirementAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequirementAccountRepository extends MongoRepository<RequirementAccount, String> {
}
