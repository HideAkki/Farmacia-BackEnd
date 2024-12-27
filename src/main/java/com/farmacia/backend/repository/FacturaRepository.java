package com.farmacia.backend.repository;

import com.farmacia.backend.entity.FacturaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends MongoRepository<FacturaEntity, String> {
}
