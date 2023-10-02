package com.aafc.pricing.application.discount.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Response object representing the discounted price.
 */
@Getter
@AllArgsConstructor
public class DiscountResponse {

    /**
     * The calculated discounted price.
     */
    private final BigDecimal discountedPrice;
}