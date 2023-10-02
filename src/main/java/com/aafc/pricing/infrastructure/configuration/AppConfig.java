package com.aafc.pricing.infrastructure.configuration;

import com.aafc.pricing.PricingDiscountDemoApplication;
import com.aafc.pricing.domain.discount.policy.count.CountBasedDiscountPolicy;
import com.aafc.pricing.domain.discount.policy.percentage.PercentageBasedDiscountPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = PricingDiscountDemoApplication.class)
@EnableTransactionManagement
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AppConfig {

    @Bean
    public CountBasedDiscountPolicy countBasedDiscountPolicy() {
        return new CountBasedDiscountPolicy();
    }

    @Bean
    public PercentageBasedDiscountPolicy percentageBasedDiscountPolicy() {
        return new PercentageBasedDiscountPolicy();
    }
}
