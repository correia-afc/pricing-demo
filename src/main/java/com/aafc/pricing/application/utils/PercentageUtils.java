package com.aafc.pricing.application.utils;

import java.math.BigDecimal;

public class PercentageUtils {

    /**
     * Checks if the given BigDecimal represents a valid percentage.
     * A valid percentage is a non-negative number less than or equal to 100.
     *
     * @param percentage The BigDecimal representing the percentage.
     * @return true if the percentage is valid, false otherwise.
     */
    public static boolean isPercentageValid(BigDecimal percentage) {
        return percentage != null &&
                percentage.compareTo(BigDecimal.ZERO) >= 0 &&
                percentage.compareTo(BigDecimal.valueOf(100)) <= 0;
    }

}
