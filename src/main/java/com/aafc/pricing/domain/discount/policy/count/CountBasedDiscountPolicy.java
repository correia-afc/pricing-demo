package com.aafc.pricing.domain.discount.policy.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.policy.DiscountPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

/**
 * A discount policy implementation that applies count based discounts to the
 * price.
 */
public class CountBasedDiscountPolicy implements DiscountPolicy<Integer, List<CountBasedDiscountConfiguration>> {

    /**
     * Calculates the discounted price based on the rice, quantity, and
     * count based discount configurations.
     *
     * @param price          The price to apply the discount on.
     * @param quantity       The quantity of products being purchased.
     * @param configurations The list of count based discount configurations to be
     *                       applied
     * @return The discounted price scaled to 2 decimal places.
     */
    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal price, Integer quantity,
            List<CountBasedDiscountConfiguration> configurations) {
        return configurations.stream()
                .filter(configuration -> quantity >= configuration.lowerQuantityLimit())
                .sorted(Comparator.comparingInt(CountBasedDiscountConfiguration::lowerQuantityLimit).reversed())
                .findFirst()
                .map(configuration -> {
                    BigDecimal discountAmount = price
                            .multiply(configuration.percentage().divide(BigDecimal.valueOf(100)));
                    return price.subtract(discountAmount).setScale(2, RoundingMode.HALF_EVEN);
                })
                .orElse(price);
    }
}
