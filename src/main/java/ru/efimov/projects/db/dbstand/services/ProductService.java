package ru.efimov.projects.db.dbstand.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.efimov.projects.db.dbstand.controllers.dtos.CreateProductRequestDTO;
import ru.efimov.projects.db.dbstand.entity.Product;
import ru.efimov.projects.db.dbstand.entity.ProductCategory;
import ru.efimov.projects.db.dbstand.exceptions.EntityAlreadyExistsException;
import ru.efimov.projects.db.dbstand.exceptions.EntityDoesNotExistException;
import ru.efimov.projects.db.dbstand.repository.CategoryRepository;
import ru.efimov.projects.db.dbstand.repository.ProductSpecifications;
import ru.efimov.projects.db.dbstand.repository.ProductsRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductsRepository productsRepository,
                          CategoryRepository categoryRepository) {
        this.productsRepository = productsRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Product createProduct(CreateProductRequestDTO dto) {
        productsRepository.getProductByProductName(dto.getName()).ifPresent((existProduct) -> {
            throw new EntityAlreadyExistsException("Product already exist, product id: " + existProduct.getProductId());
        });

        log.warn(dto.getCategoriesId().toString());
        List<ProductCategory> categoryList = categoryRepository.findAllById(dto.getCategoriesId());

        Product product = Product.builder()
                .productName(dto.getName())
                .categories(Set.copyOf(categoryList))
                .price(dto.getPrice())
                .build();

        return productsRepository.save(product);
    }

    public void deleteProduct(int id) {
        if (!productsRepository.existsById(id)) {
            throw new EntityDoesNotExistException("Product with ID: " + id + "does not exist");
        }
        productsRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct(Product product) {
        if (!productsRepository.existsById(product.getProductId())) {
            throw new EntityDoesNotExistException("Product with ID: " + product.getProductId() + "does not exist");
        }
        productsRepository.save(product);
    }

    public Page<Product> getProductByQuery(ProductQuery query) {
        List<Specification<Product>> specifications = new ArrayList<>();

        if (query.getMinimumPriceValue() != null) {
            specifications.add(ProductSpecifications.priceMoreThen(query.getMinimumPriceValue()));
        }
        if (query.getMaximumPriceValue() != null) {
            specifications.add(ProductSpecifications.priceLessThen(query.getMaximumPriceValue()));
        }
//        if (query.getContainsCategoryIds() != null && !query.getContainsCategoryIds().isEmpty()) {
//            specifications.add(ProductSpecifications.hasCategories(query.getContainsCategoryIds()));
//        }
        if (query.getQuery() != null && !query.getQuery().isBlank()) {
            specifications.add(ProductSpecifications.titleContains(query.getQuery()));
        }
        if (query.getDeliveringIsAvailable() != null) {
            specifications.add(ProductSpecifications.deliveryIsAvailable(query.getDeliveringIsAvailable()));
        }

        Specification<Product> finalSpecification = Specification.where(null);
        for (Specification<Product> specification : specifications) {
            finalSpecification = finalSpecification.and(specification);
        }

        Sort sort = switch (query.getSortedBy()) {
            case NAME_DESCENDING -> Sort.by("productName").descending();
            case DELIVERY_AVAILABLE_FIRST -> Sort.by("deliveringIsAvailable").descending();
            case PRICE_DESCENDING -> Sort.by("price").descending();
        };

        Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize(), sort);
        return productsRepository.findAll(finalSpecification, pageable);
    }
}