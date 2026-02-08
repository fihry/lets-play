package org.letsplay.letsplay.repository;

import org.letsplay.letsplay.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
    Optional<Product> findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
}