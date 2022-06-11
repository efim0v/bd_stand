package ru.efimov.projects.db.dbstand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.efimov.projects.db.dbstand.entity.Product;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
//    List<Product> getAllByPriceBetween(int minimumPrice, int maximumPrice, Pageable pageable);
    Optional<Product> getProductByProductName(String productName);
//    List<Product> getAllProductByProductNameIsLike(String query, Pageable pageable);
}
