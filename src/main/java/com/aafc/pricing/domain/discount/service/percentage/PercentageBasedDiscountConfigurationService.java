package com.aafc.pricing.domain.discount.service.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;

import java.util.UUID;

/**
 * Service interface for managing percentage based discount configurations.
 */
public interface PercentageBasedDiscountConfigurationService {

    /**
     * Retrieves the percentage based discount for a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return The percentage based discount configuration applicable to the
     *         product.
     */
    PercentageBasedDiscountConfiguration getPercentageDiscountConfiguration(UUID productId);

    /**
     * Saves a percentage based discount for a specific product.
     *
     * @param productId               The unique identifier of the product.
     * @param percentageConfiguration The percentage discount configuration to be
     *                                saved.
     */
    void savePercentageDiscountConfiguration(UUID productId,
            PercentageBasedDiscountConfiguration percentageConfiguration);
}
