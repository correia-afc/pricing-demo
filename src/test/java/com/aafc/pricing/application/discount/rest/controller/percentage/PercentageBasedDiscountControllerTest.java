package com.aafc.pricing.application.discount.rest.controller.percentage;

import com.aafc.pricing.domain.discount.model.percentage.PercentageBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.service.percentage.PercentageBasedDiscountConfigurationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PercentageBasedDiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PercentageBasedDiscountConfigurationService percentageService;

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testGetPercentageDiscountConfiguration() throws Exception {

        UUID productId = UUID.randomUUID();
        PercentageBasedDiscountConfiguration configuration = new PercentageBasedDiscountConfiguration(BigDecimal.TEN);
        
        when(percentageService.getPercentageDiscountConfiguration(productId)).thenReturn(configuration);

        mockMvc.perform(
                get("/api/discounts/percentage/{productId}", productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.percentage").value(10));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testSavePercentageDiscountConfiguration() throws Exception {
        String jsonBody = "{\"percentage\": 15}";

        mockMvc.perform(
                post("/api/discounts/percentage/{productId}", UUID.randomUUID())
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
