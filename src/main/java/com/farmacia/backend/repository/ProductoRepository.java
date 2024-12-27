package com.farmacia.backend.repository;

import com.farmacia.backend.entity.ProductoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<ProductoEntity, String> {
}
