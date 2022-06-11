package ru.efimov.projects.db.dbstand.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductResponseDTO {
    @JsonProperty("product_id")
    final int productID;
}
