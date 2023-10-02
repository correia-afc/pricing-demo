package com.aafc.pricing.domain.discount.policy;

import java.math.BigDecimal;

/**
 * A generic interface representing a discount policy for various discount
 * policies.
 *
 * @param <T> The type of input parameter specific to the discount policy.
 * @param <C> The type of configuration parameter specific to the discount
 *            policy.
 */
public interface DiscountPolicy<T, C> {
    /**
     * Calculates the discounted price based on the provided input parameters,
     * price, and configuration.
     *
     * @param price         The price to apply the discount on.
     * @param input         The input parameters specific to the discount policy.
     * @param configuration The configuration parameters specific to the discount
     *                      policy.
     * @return The discounted price.
     */
    BigDecimal calculateDiscountedPrice(BigDecimal price, T input, C configuration);
}