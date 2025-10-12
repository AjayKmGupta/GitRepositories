package com.learn.mongocluster.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@TypeAlias("product")
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;

}
