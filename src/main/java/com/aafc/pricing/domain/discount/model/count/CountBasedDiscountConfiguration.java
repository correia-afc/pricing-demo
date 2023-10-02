package com.aafc.pricing.domain.discount.model.count;

import java.math.BigDecimal;

/**
 * Represents a configuration entry for count based discounts:
 * 
 * The lower quantity limit for applying the discount. Products with quantities
 * greater than or equal to this limit will have the discount applied.
 * 
 * The percentage discount to be applied for quantities greater than or equal to
 * the lower quantity limit.
 */

public record CountBasedDiscountConfiguration(Integer lowerQuantityLimit, BigDecimal percentage) {
};
