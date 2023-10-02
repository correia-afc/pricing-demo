package com.aafc.pricing.infrastructure.repository.discount.h2.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.repository.percentage.PercentageBasedDiscountConfigurationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link PercentageBasedDiscountConfigurationRepository}
 * using H2
 * database
 * with an underlying {@link JpaPercentageBasedDiscountConfigurationRepository}
 * for
 * persistence.
 */
@Component
public class H2PercentageBasedDiscountConfigurationRepository
        implements PercentageBasedDiscountConfigurationRepository {

    private final JpaPercentageBasedDiscountConfigurationRepository jpaRepository;

    public H2PercentageBasedDiscountConfigurationRepository(
            JpaPercentageBasedDiscountConfigurationRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PercentageBasedDiscountConfiguration> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId).map(this::convertFromEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UUID productId, PercentageBasedDiscountConfiguration configuration) {
        jpaRepository.findByProductId(productId).ifPresentOrElse(entity -> {
            entity.setPercentage(configuration.percentage());
            jpaRepository.save(entity);
        }, () -> {
            jpaRepository.save(convertToEntity(productId, configuration));
        });
    }

    // simple converter methods, not used anywhere else, no need to have a
    // separate class for now
    private PercentageBasedDiscountConfiguration convertFromEntity(PercentageBasedDiscountConfigurationEntity entity) {
        return new PercentageBasedDiscountConfiguration(entity.getPercentage());
    }

    private PercentageBasedDiscountConfigurationEntity convertToEntity(UUID productId,
            PercentageBasedDiscountConfiguration configuration) {
        return PercentageBasedDiscountConfigurationEntity
                .builder()
                .productId(productId)
                .percentage(configuration.percentage())
                .build();
    }
}
