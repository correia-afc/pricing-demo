package com.aafc.pricing.domain.discount.service.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing count based discount configurations.
 */
public interface CountBasedDiscountConfigurationService {

    /**
     * Retrieves the count based discount configuration for a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return The count based discount configuration for the product.
     */
    List<CountBasedDiscountConfiguration> getCountBasedDiscountConfigurations(UUID productId);

    /**
     * Saves count based discount configurations for a specific product.
     *
     * @param productId      The unique identifier of the product.
     * @param configurations The list of count based discount configurations to be
     *                       saved.
     */
    void saveCountBasedDiscountConfigurations(UUID productId, List<CountBasedDiscountConfiguration> configurations);
}
