package com.aafc.pricing.application.discount.service.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.repository.count.CountBasedDiscountConfigurationRepository;
import com.aafc.pricing.domain.discount.service.count.CountBasedDiscountConfigurationService;
import com.aafc.pricing.domain.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.aafc.pricing.application.utils.PercentageUtils.isPercentageValid;

/**
 * Implementation of the {@link CountBasedDiscountConfigurationService}
 * interface.
 * Provides functionality to manage count based discount configurations.
 * 
 * Note: To improve performance, the getCountBasedDiscountConfigurations could
 * benefit of a future cache implementation
 */
@Service
public class CountBasedDiscountConfigurationServiceImpl implements CountBasedDiscountConfigurationService {

    private final CountBasedDiscountConfigurationRepository repository;
    private final ProductService productService;

    public CountBasedDiscountConfigurationServiceImpl(CountBasedDiscountConfigurationRepository repository,
            ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountBasedDiscountConfiguration> getCountBasedDiscountConfigurations(UUID productId) {
        // Validate product exists
        productService.getProduct(productId);

        return repository.findByProductId(productId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCountBasedDiscountConfigurations(UUID productId,
            List<CountBasedDiscountConfiguration> configurations) {

        if (configurations == null) {
            throw new IllegalArgumentException("Count based configuration list cannot be null");
        }

        configurations.forEach(configuration -> {
            if (!isPercentageValid(configuration.percentage())) {
                throw new IllegalArgumentException("Percentage must be between 0 and 100");
            }
        });

        // Validate product exists
        productService.getProduct(productId);

        repository.saveAll(productId, configurations);
    }
}
