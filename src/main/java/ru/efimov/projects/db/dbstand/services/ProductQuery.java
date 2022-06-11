package ru.efimov.projects.db.dbstand.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.efimov.projects.db.dbstand.entity.ProductCategory;

import java.util.Set;

@Data
@Builder
public class ProductQuery {
    @JsonProperty("minimum_price_value")
    private Integer minimumPriceValue;

    @JsonProperty("maximum_price_value")
    private Integer maximumPriceValue;

    @JsonProperty("contains_category")
    private Set<Integer> containsCategoryIds;

    @JsonProperty("delivering_is_available")
    private Boolean deliveringIsAvailable;

    @JsonProperty("sorted_by")
    private SortedBy sortedBy;

    private String query;

    private int pageSize;
    private int pageNumber;
}

enum SortedBy {
    PRICE_DESCENDING,
    DELIVERY_AVAILABLE_FIRST,
    NAME_DESCENDING
}

