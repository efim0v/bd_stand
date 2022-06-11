package ru.efimov.projects.db.dbstand.controllers.dtos;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Data
public class CreateProductRequestDTO {

    @JsonProperty("price")
    @NotNull
    private int price;

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("delivering_is_available")
    private boolean deliveringIsAvailable;

    @JsonProperty("categories_id")
    private List<Integer> categoriesId = Collections.emptyList();
}