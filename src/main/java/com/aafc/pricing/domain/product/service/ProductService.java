package com.aafc.pricing.domain.product.service;

import com.aafc.pricing.domain.product.model.Product;

import java.util.UUID;

public interface ProductService {

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param id The unique identifier of the product.
     * @return The product with the given ID.
     * @throws ProductNotFoundException if the product with the given ID is not
     *                                  found.
     */
    Product getProduct(UUID id);
}
