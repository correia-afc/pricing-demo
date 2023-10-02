package com.aafc.pricing.domain.discount.model.percentage;

import java.math.BigDecimal;

/**
 * Represents a configuration entry for percentage based discounts.
 */

public record PercentageBasedDiscountConfiguration(BigDecimal percentage) {
}
