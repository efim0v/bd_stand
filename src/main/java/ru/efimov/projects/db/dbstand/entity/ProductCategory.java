package ru.efimov.projects.db.dbstand.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "product_category")
public class ProductCategory {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "category_name", unique = true)
    @JsonProperty("category_name")
    private String categoryName;

    @ManyToMany
    private Set<Product> products;

    private String description;
}