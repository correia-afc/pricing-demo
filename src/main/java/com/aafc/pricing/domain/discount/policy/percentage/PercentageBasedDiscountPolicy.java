package com.aafc.pricing.domain.discount.policy.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.policy.DiscountPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A discount policy implementation that applies a percentage based discount to
 * the price.
 */
public class PercentageBasedDiscountPolicy implements DiscountPolicy<Void, PercentageBasedDiscountConfiguration> {

    /**
     * Calculates the discounted price based on the price and the configured
     * percentage.
     *
     * @param price         The price to apply the discount on.
     * @param input         No additional input required.
     * @param configuration The percentage configuration to be applied.
     * @return The discounted price scaled to 2 decimal places.
     */
    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal price, Void input,
            PercentageBasedDiscountConfiguration configuration) {
        BigDecimal discountAmount = price.multiply(configuration.percentage().divide(BigDecimal.valueOf(100)));
        return price.subtract(discountAmount).setScale(2, RoundingMode.HALF_EVEN);
    }
}
