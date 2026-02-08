package org.letsplay.letsplay.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document(collation = "products")
public class Product {
    @Id private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private UUID userUuid;
    private LocalDateTime createdAt= LocalDateTime.now();
    private LocalDateTime updatedAt= LocalDateTime.now();
}
