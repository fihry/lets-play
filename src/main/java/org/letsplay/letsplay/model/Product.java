package org.letsplay.letsplay.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.query.StringBasedMongoQuery;

import java.util.UUID;

@Document(collation = "products")
public class Product {
    @Id private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private UUID userUuid;
}
