package com.aafc.pricing.application.discount.rest.controller;

import com.aafc.pricing.application.discount.rest.response.DiscountResponse;
import com.aafc.pricing.domain.discount.service.DiscountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * REST controller for managing product discounts.
 */
@RestController
@RequestMapping(path = "/api/public/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Calculates the discounted price of a product based on a percentage discount.
     *
     * @param productId The unique identifier of the product.
     * @return A ResponseEntity containing the calculated discounted price
     */
    @GetMapping("/{productId}/discount/percentage")
    public ResponseEntity<DiscountResponse> calculateDiscountedPriceByPercentage(@PathVariable UUID productId) {
        return ResponseEntity
                .ok(new DiscountResponse(discountService.calculateDiscountedPriceByPercentage(productId)));
    }

    /**
     * Calculates the discounted price of a product based on the given quantity.
     *
     * @param productId The unique identifier of the product.
     * @param quantity  The quantity of products being purchased.
     * @return A ResponseEntity containing the calculated discounted price
     */
    @GetMapping("/{productId}/discount/count")
    public ResponseEntity<DiscountResponse> calculateDiscountedPriceByQuantity(
            @PathVariable UUID productId, @RequestParam int quantity) {
        return ResponseEntity
                .ok(new DiscountResponse(discountService.calculateDiscountedPriceByQuantity(productId, quantity)));
    }
}
