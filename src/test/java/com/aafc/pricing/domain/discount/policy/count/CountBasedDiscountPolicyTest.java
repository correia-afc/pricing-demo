package com.aafc.pricing.domain.discount.policy.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBasedDiscountPolicyTest {

    @Test
    public void testCountBasedDiscountPolicy() {
        CountBasedDiscountPolicy discountPolicy = new CountBasedDiscountPolicy();
        BigDecimal price = new BigDecimal("100.00");

        // Test with quantity less than 10 (0% discount)
        BigDecimal discountedPrice = discountPolicy.calculateDiscountedPrice(price, 5,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("100.00"), discountedPrice);

        // Test with quantity equal to 10 (10% discount)
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, 10,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("90.00"), discountedPrice);

        // Test with quantity greater than 10 and less than 20 (10% discount)
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, 15,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("90.00"), discountedPrice);

        // Change product price
        price = new BigDecimal("500.00");

        // Test with quantity equal to 20 (15% discount)
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, 20,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("425.00"), discountedPrice);

        // Test with quantity greater than 20 and less than 50 (15% discount)
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, 33,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("425.00"), discountedPrice);

        // Test with quantity equal to 50 (20% discount)
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, 50,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("400.00"), discountedPrice);

        // Test with quantity greater than 50 (20% discount)
        discountedPrice = discountPolicy.calculateDiscountedPrice(price, 78,
                getSampleCountBasedConfigurations());
        assertEquals(new BigDecimal("400.00"), discountedPrice);
    }

    private List<CountBasedDiscountConfiguration> getSampleCountBasedConfigurations() {
        return List.of(
                // 15% discount for quantities greater than 20
                new CountBasedDiscountConfiguration(10, new BigDecimal("10")),
                // 15% discount for quantities greater than 20
                new CountBasedDiscountConfiguration(20, new BigDecimal("15")),
                // 20% discount for quantities greater than 50
                new CountBasedDiscountConfiguration(50, new BigDecimal("20")));
    }
}
