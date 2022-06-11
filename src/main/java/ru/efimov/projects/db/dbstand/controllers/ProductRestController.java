package ru.efimov.projects.db.dbstand.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.efimov.projects.db.dbstand.controllers.dtos.CreateProductRequestDTO;
import ru.efimov.projects.db.dbstand.controllers.dtos.CreateProductResponseDTO;
import ru.efimov.projects.db.dbstand.entity.Product;
import ru.efimov.projects.db.dbstand.services.ProductQuery;
import ru.efimov.projects.db.dbstand.services.ProductService;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponseDTO createProduct(CreateProductRequestDTO dto) {
        Product createdProduct = productService.createProduct(dto);
        return new CreateProductResponseDTO(createdProduct.getProductId());
    }

    @PostMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> getProduct(ProductQuery query) {
        return productService.getProductByQuery(query);
    }
}

