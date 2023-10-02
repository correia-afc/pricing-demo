package com.aafc.pricing.application.discount.rest.controller.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.service.count.CountBasedDiscountConfigurationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing count-based discount configurations.
 */
@RestController
@RequestMapping("/api/discounts/count")
public class CountBasedDiscountController {

    private final CountBasedDiscountConfigurationService configurationService;

    public CountBasedDiscountController(CountBasedDiscountConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Retrieves the count-based discount configurations for a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return A ResponseEntity containing the list of count-based discount
     *         configurations in a JSON response.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<List<CountBasedDiscountConfiguration>> getCountBasedDiscountConfigurations(
            @PathVariable UUID productId) {
        List<CountBasedDiscountConfiguration> configurations = configurationService
                .getCountBasedDiscountConfigurations(productId);
        return ResponseEntity.ok(configurations);
    }

    /**
     * Saves count-based discount configurations for a specific product.
     *
     * @param productId      The unique identifier of the product.
     * @param configurations The list of count-based discount configurations to be
     *                       saved.
     * @return A ResponseEntity with a status code indicating the success of the
     *         operation.
     * @throws IllegalArgumentException If the configurations are null or if any
     *                                  percentage is not valid.
     */
    @PostMapping("/{productId}")
    public ResponseEntity<Void> saveCountBasedDiscountConfigurations(
            @PathVariable UUID productId,
            @RequestBody List<CountBasedDiscountConfiguration> configurations) {
        configurationService.saveCountBasedDiscountConfigurations(productId, configurations);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO: implement remaining CRUD operations
}
