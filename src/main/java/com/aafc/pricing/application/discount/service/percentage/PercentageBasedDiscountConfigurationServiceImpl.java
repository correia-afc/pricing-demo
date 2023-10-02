package com.aafc.pricing.application.discount.service.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.repository.percentage.PercentageBasedDiscountConfigurationRepository;
import com.aafc.pricing.domain.discount.service.percentage.PercentageBasedDiscountConfigurationService;
import com.aafc.pricing.domain.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static com.aafc.pricing.application.utils.PercentageUtils.isPercentageValid;

/**
 * Implementation of the {@link PercentageBasedDiscountConfigurationService}
 * interface.
 * Provides functionality to manage percentage based discount configurations.
 * 
 * Note: To improve performance, the getPercentageDiscountConfiguration could
 * benefit of a future cache implementation
 */
@Service
public class PercentageBasedDiscountConfigurationServiceImpl implements PercentageBasedDiscountConfigurationService {

    private final PercentageBasedDiscountConfigurationRepository repository;
    private final ProductService productService;

    public PercentageBasedDiscountConfigurationServiceImpl(PercentageBasedDiscountConfigurationRepository repository,
            ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PercentageBasedDiscountConfiguration getPercentageDiscountConfiguration(UUID productId) {
        // Validate product exists
        productService.getProduct(productId);

        // If no configuration is found, return a 0% discount
        return repository.findByProductId(productId).orElse(new PercentageBasedDiscountConfiguration(BigDecimal.ZERO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void savePercentageDiscountConfiguration(UUID productId,
            PercentageBasedDiscountConfiguration percentageConfiguration) {

        if (!isPercentageValid(percentageConfiguration.percentage())) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }

        // Validate product exists
        productService.getProduct(productId);

        repository.save(productId, percentageConfiguration);
    }

}