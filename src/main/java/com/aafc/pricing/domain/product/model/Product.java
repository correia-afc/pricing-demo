package com.aafc.pricing.domain.product.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a product in the system.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    /**
     * Unique identifier for the product.
     */
    private final UUID id;

    /**
     * Name of the product.
     */
    @EqualsAndHashCode.Exclude
    private final String name;

    /**
     * Base price of the product.
     */
    @EqualsAndHashCode.Exclude
    private final BigDecimal price;
}
