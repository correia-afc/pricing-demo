package com.aafc.pricing.application.discount.service.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.service.count.CountBasedDiscountConfigurationService;
import com.aafc.pricing.domain.product.model.Product;
import com.aafc.pricing.domain.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountBasedDiscountConfigurationServiceImplTest {

    @Autowired
    CountBasedDiscountConfigurationService countBasedService;

    @MockBean
    private ProductService productService;

    @Test
    void testSaveAndGetCountBasedDiscountConfigurations() {
        UUID productId = UUID.randomUUID();

        when(productService.getProduct(productId)).thenReturn(new Product(productId, null, null));

        assertTrue(countBasedService.getCountBasedDiscountConfigurations(productId).isEmpty());
        countBasedService.saveCountBasedDiscountConfigurations(productId, List.of(
                new CountBasedDiscountConfiguration(1, BigDecimal.ONE),
                new CountBasedDiscountConfiguration(10, BigDecimal.TEN)));
        assertTrue(countBasedService.getCountBasedDiscountConfigurations(productId).size() == 2);
    }

    @Test
    void testSaveCountBasedDiscountConfigurations_invalidPercentage() {
        assertThrows(IllegalArgumentException.class,
                () -> countBasedService.saveCountBasedDiscountConfigurations(UUID.randomUUID(),
                        List.of(new CountBasedDiscountConfiguration(1, null))));
        assertThrows(IllegalArgumentException.class,
                () -> countBasedService.saveCountBasedDiscountConfigurations(UUID.randomUUID(),
                        List.of(new CountBasedDiscountConfiguration(1, new BigDecimal(-1)))));
        assertThrows(IllegalArgumentException.class,
                () -> countBasedService.saveCountBasedDiscountConfigurations(UUID.randomUUID(),
                        List.of(new CountBasedDiscountConfiguration(1, new BigDecimal(100.01)))));
    }
}
