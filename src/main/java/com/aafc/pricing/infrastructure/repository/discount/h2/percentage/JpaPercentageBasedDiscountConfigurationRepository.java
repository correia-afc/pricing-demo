package com.aafc.pricing.infrastructure.repository.discount.h2.percentage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaPercentageBasedDiscountConfigurationRepository
        extends JpaRepository<PercentageBasedDiscountConfigurationEntity, Long> {

    Optional<PercentageBasedDiscountConfigurationEntity> findByProductId(UUID productId);
    void deleteByProductId(UUID productId);
}
