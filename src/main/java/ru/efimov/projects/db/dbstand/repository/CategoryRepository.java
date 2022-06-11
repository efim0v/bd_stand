package ru.efimov.projects.db.dbstand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.efimov.projects.db.dbstand.entity.ProductCategory;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Integer> { }
