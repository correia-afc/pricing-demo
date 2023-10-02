package com.aafc.pricing.infrastructure.repository.discount.h2.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.repository.count.CountBasedDiscountConfigurationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of {@link CountBasedDiscountConfigurationRepository} using H2
 * database
 * with an underlying {@link JpaCountBasedDiscountConfigurationRepository} for
 * persistence.
 * 
 */
@Component
public class H2CountBasedDiscountConfigurationRepository implements CountBasedDiscountConfigurationRepository {

    private final JpaCountBasedDiscountConfigurationRepository jpaRepository;

    public H2CountBasedDiscountConfigurationRepository(JpaCountBasedDiscountConfigurationRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountBasedDiscountConfiguration> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId).stream().map(this::convertFromEntity).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveAll(UUID productId, List<CountBasedDiscountConfiguration> configurations) {
        jpaRepository.deleteByProductId(productId);
        jpaRepository.saveAll(configurations.stream().map(configuration -> {
            return convertToEntity(productId, configuration);
        }).toList());
    }

    // simple converter methtods, not used anywhere else, no need to have a
    // separate class for now
    private CountBasedDiscountConfiguration convertFromEntity(CountBasedDiscountConfigurationEntity entity) {
        return new CountBasedDiscountConfiguration(entity.getLowerQuantityLimit(), entity.getPercentage());
    }

    private CountBasedDiscountConfigurationEntity convertToEntity(UUID productId,
            CountBasedDiscountConfiguration configuration) {
        return CountBasedDiscountConfigurationEntity
                .builder()
                .productId(productId)
                .lowerQuantityLimit(configuration.lowerQuantityLimit())
                .percentage(configuration.percentage())
                .build();
    }
}
