package com.aafc.pricing.application.discount.rest.controller.count;

import com.aafc.pricing.domain.discount.model.count.CountBasedDiscountConfiguration;
import com.aafc.pricing.domain.discount.service.count.CountBasedDiscountConfigurationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CountBasedDiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountBasedDiscountConfigurationService countService;

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testGetCountBasedDiscountConfigurations() throws Exception {
        UUID productId = UUID.randomUUID();
        List<CountBasedDiscountConfiguration> configurations = List
                .of(new CountBasedDiscountConfiguration(5, BigDecimal.TEN));

        when(countService.getCountBasedDiscountConfigurations(productId)).thenReturn(configurations);

        mockMvc.perform(get("/api/discounts/count/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].lowerQuantityLimit").value(5));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    void testSaveCountBasedDiscountConfigurations() throws Exception {
        UUID productId = UUID.randomUUID();
        String jsonBody = "[{\"quantity\": 5, \"percentage\": 10}]";

        mockMvc.perform(post("/api/discounts/count/{productId}", productId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}