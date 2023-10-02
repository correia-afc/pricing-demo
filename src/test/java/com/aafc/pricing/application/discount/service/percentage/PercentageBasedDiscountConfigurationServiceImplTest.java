package com.aafc.pricing.application.discount.service.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.service.percentage.PercentageBasedDiscountConfigurationService;
import com.aafc.pricing.domain.product.model.Product;
import com.aafc.pricing.domain.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class PercentageBasedDiscountConfigurationServiceImplTest {

    @Autowired
    PercentageBasedDiscountConfigurationService percentageService;

    @MockBean
    private ProductService productService;

    @Test
    void testSaveAndGetPercentageDiscountConfiguration() {
        UUID productId = UUID.randomUUID();

        when(productService.getProduct(productId)).thenReturn(new Product(productId, null, null));

        assertTrue(
                percentageService.getPercentageDiscountConfiguration(productId).percentage().equals(BigDecimal.ZERO));
        percentageService.savePercentageDiscountConfiguration(productId,
                new PercentageBasedDiscountConfiguration(new BigDecimal("10.00")));
        assertTrue(
                percentageService.getPercentageDiscountConfiguration(productId).percentage()
                        .equals(new BigDecimal("10.00")));
    }

    @Test
    void testSavePercentageDiscountConfiguration_invalidPercentage() {
        assertThrows(IllegalArgumentException.class,
                () -> percentageService.savePercentageDiscountConfiguration(UUID.randomUUID(),
                        new PercentageBasedDiscountConfiguration(null)));
        assertThrows(IllegalArgumentException.class,
                () -> percentageService.savePercentageDiscountConfiguration(UUID.randomUUID(),
                        new PercentageBasedDiscountConfiguration(new BigDecimal(-1))));
        assertThrows(IllegalArgumentException.class,
                () -> percentageService.savePercentageDiscountConfiguration(UUID.randomUUID(),
                        new PercentageBasedDiscountConfiguration(new BigDecimal(100.01))));
    }
}
