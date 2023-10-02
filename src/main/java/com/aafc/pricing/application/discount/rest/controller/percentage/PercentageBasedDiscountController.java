package com.aafc.pricing.application.discount.rest.controller.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.service.percentage.PercentageBasedDiscountConfigurationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for managing percentage-based discount configurations.
 */
@RestController
@RequestMapping(path = "/api/discounts/percentage", produces = MediaType.APPLICATION_JSON_VALUE)
public class PercentageBasedDiscountController {

    private final PercentageBasedDiscountConfigurationService configurationService;

    public PercentageBasedDiscountController(PercentageBasedDiscountConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Retrieves the percentage based discount configuration for a specific product.
     *
     * @param productId The unique identifier of the product.
     * @return A ResponseEntity containing the percentage based discount configuration in a JSON response.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<PercentageBasedDiscountConfiguration> getPercentageDiscountConfiguration(
            @PathVariable UUID productId) {
        PercentageBasedDiscountConfiguration configuration = configurationService.getPercentageDiscountConfiguration(productId);
        return ResponseEntity.ok(configuration);
    }

    /**
     * Saves (upsert) a percentage based discount configuration for a specific product.
     *
     * @param productId               The unique identifier of the product.
     * @param percentageConfiguration The percentage discount configuration to be saved.
     * @return A ResponseEntity with a status code indicating the success of the operation.
     */
    @PostMapping("/{productId}")
    public ResponseEntity<Void> savePercentageDiscountConfiguration(
            @PathVariable UUID productId,
            @RequestBody PercentageBasedDiscountConfiguration percentageConfiguration) {

        configurationService.savePercentageDiscountConfiguration(productId, percentageConfiguration);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO: implement remaining CRUD operations
}
