package com.aafc.pricing.domain.discount.repository.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing count based discount configurations.
 */
public interface CountBasedDiscountConfigurationRepository {

    /**
     * Retrieves the count based discount configurations for a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return The list of count based discount configurations for the product.
     */
    List<CountBasedDiscountConfiguration> findByProductId(UUID productId);

    /**
     * Saves count based discount configurations for a specific product.
     *
     * @param productId      The unique identifier of the product.
     * @param configurations The list of count based discount configurations to be
     *                       saved.
     */
    void saveAll(UUID productId, List<CountBasedDiscountConfiguration> configurations);
}
