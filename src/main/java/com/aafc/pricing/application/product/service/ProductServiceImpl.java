package com.aafc.pricing.application.product.service;

import com.aafc.pricing.application.exception.NotFoundException;
import com.aafc.pricing.domain.product.model.Product;
import com.aafc.pricing.domain.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link ProductService} interface.
 * Provides functionality to retrieve products.
 * 
 * Note: This is a mocked implementation for demo purposes.
 * Assumption: there is a product catalog service in the system so this class
 * would invoke it.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products;

    public ProductServiceImpl() {
        // Mocked data: 5 products with random IDs, names, and prices
        products = List.of(
                new Product(UUID.fromString("bf39d6eb-d3ca-4d53-9a47-03b440ef24d0"), "Product 1",
                        new BigDecimal("100.00")),
                new Product(UUID.fromString("ed1de060-5ead-475f-b57e-d9971dace701"), "Product 2",
                        new BigDecimal("200.00")),
                new Product(UUID.fromString("90938243-7dd0-4f56-874d-18602f5c6d20"), "Product 3",
                        new BigDecimal("300.00")),
                new Product(UUID.fromString("792440e5-1505-419d-9b77-9b268cd932a1"), "Product 4",
                        new BigDecimal("400.00")),
                new Product(UUID.fromString("70de7ae6-03f5-4dd0-830a-19bbd3085ac1"), "Product 5",
                        new BigDecimal("500.00")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product getProduct(UUID id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product [" + id + "] not found."));
    }
}
