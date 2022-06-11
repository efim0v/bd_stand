package ru.efimov.projects.db.dbstand.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.efimov.projects.db.dbstand.entity.Product;
import ru.efimov.projects.db.dbstand.entity.ProductCategory;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.Set;

public class ProductSpecifications {
    public static Specification<Product> priceLessThen(int maxPrice) {
        return (product, cq, cb) -> cb.lessThan(product.get("price"), maxPrice);
    }

    public static Specification<Product> priceMoreThen(int minPrice) {
        return (product, cq, cb) -> cb.not(cb.lessThan(product.get("price"), minPrice));
    }

    public static Specification<Product> deliveryIsAvailable(boolean isAvailable) {
        return (product, cq, cb) -> cb.equal(product.get("delivering_is_available"), isAvailable);
    }

    public static Specification<Product> titleContains(String title) {
        return (product, cq, cb) -> cb.like(product.<String>get("productName"), "%" + title + "%");
    }

//    public static Specification<Product> hasCategories(Set<Integer> categoryIdsSet) {
//        return (product, cq, cb) -> {
//            Expression<CategoryRepository> categoryExpression = product.get("category");
//            Predicate categoryPredicate = categoryExpression.in(categoryIdsSet);
//            Join<Product, ProductCategory> categoryJoin = product.join("categories");
//            return cb.(categoryJoin.<Integer>get("categoryId"), categoryIdsSet);
//        };
//    }
}
