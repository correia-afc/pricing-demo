package com.aafc.pricing.domain.discount.repository.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing percentage based discount configurations.
 */
public interface PercentageBasedDiscountConfigurationRepository {

    /**
     * Retrieves the percentage based configuration for a specific product.
     *
     * @param productId The UUID of the product.
     * @return The percentage based configuration applicable to the product.
     */
    Optional<PercentageBasedDiscountConfiguration> findByProductId(UUID productId);

    /**
     * Saves (upsert) a percentage based discount for a specific product.
     *
     * @param productId               The UUID of the product.
     * @param percentageConfiguration The percentage based configuration to be
     *                                saved.
     */
    void save(UUID productId, PercentageBasedDiscountConfiguration percentageConfiguration);
}
