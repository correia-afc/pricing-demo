package com.aafc.pricing.domain.discount.policy.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentageBasedDiscountPolicyTest {

    @Test
    public void testPercentageBasedDiscountPolicy() {
        PercentageBasedDiscountPolicy discountPolicy = new PercentageBasedDiscountPolicy();

        // Test with product price of 100 and 0% discount
        BigDecimal price = new BigDecimal("100");
        PercentageBasedDiscountConfiguration percentageConfiguration = new PercentageBasedDiscountConfiguration(BigDecimal.ZERO);
        BigDecimal discountedPrice = discountPolicy.calculateDiscountedPrice(price, null, percentageConfiguration);
        assertEquals(new BigDecimal("100.00"), discountedPrice);

        // Test with product price of 100 and 10% discount
        price = new BigDecimal("100");
        percentageConfiguration = new PercentageBasedDiscountConfiguration(new BigDecimal("10"));
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, null, percentageConfiguration);
        assertEquals(new BigDecimal("90.00"), discountedPrice);

        // Test with product price of 50 and 20% discount
        price = new BigDecimal("50");
        percentageConfiguration = new PercentageBasedDiscountConfiguration(new BigDecimal("20"));
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, null, percentageConfiguration);
        assertEquals(new BigDecimal("40.00"), discountedPrice);
    }
}
