package com.aafc.pricing.infrastructure.repository.discount.h2.count;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaCountBasedDiscountConfigurationRepository
        extends JpaRepository<CountBasedDiscountConfigurationEntity, Long> {

    List<CountBasedDiscountConfigurationEntity> findByProductId(UUID productId);
    void deleteByProductId(UUID productId);
}
