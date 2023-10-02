package com.aafc.pricing.domain.discount.service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Service interface for managing product discounts.
 */
public interface DiscountService {

    /**
     * Calculates the discounted price of a product based on configured percentage.
     *
     * @param productId  The unique identifier of the product.
     * @return The discounted price as a BigDecimal.
     */
    BigDecimal calculateDiscountedPriceByPercentage(UUID productId);

    /**
     * Calculates the discounted price of a product based on the given quantity.
     *
     * @param productId       The unique identifier of the product.
     * @param quantity        The quantity of products being purchased.
     * @return The discounted price as a BigDecimal.
     */
    BigDecimal calculateDiscountedPriceByQuantity(UUID productId, int quantity);
}
