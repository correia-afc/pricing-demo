package com.aafc.pricing.application.discount.service;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.policy.count.CountBasedDiscountPolicy;
import com.aafc.pricing.domain.discount.policy.percentage.PercentageBasedDiscountPolicy;
import com.aafc.pricing.domain.discount.service.DiscountService;
import com.aafc.pricing.domain.discount.service.count.CountBasedDiscountConfigurationService;
import com.aafc.pricing.domain.discount.service.percentage.PercentageBasedDiscountConfigurationService;
import com.aafc.pricing.domain.product.model.Product;
import com.aafc.pricing.domain.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link DiscountService} interface.
 * Provides functionality to calculate product discounts.
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    private final CountBasedDiscountConfigurationService countBasedService;
    private final PercentageBasedDiscountConfigurationService percentageBasedService;
    private final ProductService productService;
    private final CountBasedDiscountPolicy countBasedDiscountPolicy;
    private final PercentageBasedDiscountPolicy percentageBasedDiscountPolicy;

    public DiscountServiceImpl(CountBasedDiscountConfigurationService countBasedService,
            PercentageBasedDiscountConfigurationService percentageBasedService, ProductService productService,
            CountBasedDiscountPolicy countBasedDiscountPolicy,
            PercentageBasedDiscountPolicy percentageBasedDiscountPolicy) {
        this.countBasedService = countBasedService;
        this.percentageBasedService = percentageBasedService;
        this.productService = productService;
        this.countBasedDiscountPolicy = countBasedDiscountPolicy;
        this.percentageBasedDiscountPolicy = percentageBasedDiscountPolicy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateDiscountedPriceByPercentage(UUID productId) {

        Product product = productService.getProduct(productId);
        PercentageBasedDiscountConfiguration percentageBasedDiscountConfiguration = percentageBasedService
                .getPercentageDiscountConfiguration(productId);

        return percentageBasedDiscountPolicy.calculateDiscountedPrice(product.getPrice(), null,
                percentageBasedDiscountConfiguration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateDiscountedPriceByQuantity(UUID productId, int quantity) {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productService.getProduct(productId);
        List<CountBasedDiscountConfiguration> countBasedDiscountConfigurations = countBasedService
                .getCountBasedDiscountConfigurations(productId);

        return countBasedDiscountPolicy.calculateDiscountedPrice(product.getPrice(), quantity,
                countBasedDiscountConfigurations);
    }
}
