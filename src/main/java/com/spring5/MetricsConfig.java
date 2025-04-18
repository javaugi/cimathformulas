/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheusmetrics.PrometheusConfig;
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author javaugi
 */
@Configuration
public class MetricsConfig {
    /*
    Additional Notes
    If using Spring Boot 2.x+, you don't need to manually create this bean - it's already provided.
    For production, consider adding a monitoring system-specific registry (Prometheus, Datadog, etc.).
    Make sure to include the appropriate Micrometer dependency for your monitoring system.    
    */


    /*
    Creating a Spring-Managed MeterRegistry Bean
      To create a Spring-managed MeterRegistry bean (from Micrometer) in your configuration, you have several options depending 
        on your monitoring system and Spring Boot version.

        Basic Configuration
        1. Simple MeterRegistry Bean (in-memory)
    */
    //@Bean
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }
    
    /*
    2. Using Spring Boot Autoconfiguration (Recommended)
        If you're using Spring Boot, it already provides autoconfiguration for Micrometer. Just add the dependency:   
            <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-core</artifactId>
        </dependency>
        Spring Boot will automatically create and configure a MeterRegistry bean.
    */
    
    /*
        For Specific Monitoring Systems
        3. Prometheus Example    
    */

    /**
     *
     * @param collectorRegistry
     * @return
     */
    /*
    @Bean
    public MeterRegistry meterRegistry(CollectorRegistry collectorRegistry) {
        return new PrometheusMeterRegistry(registry -> collectorRegistry);
    }    

    @Bean
    public PrometheusMeterRegistry prometheusMeterRegistry(Clock clock) {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("prometheus.descriptions", "true");
        configMap.put("prometheus.step", "1m");
        configMap.put("prometheus.scrape-timeout", "10s");

        // Example of a custom configuration implementation
        PrometheusConfig customConfig = new PrometheusConfig() {
            private final Map<String, String> properties = configMap;

            @Override
            public String get(String key) {
                return properties.get(key);
            }

            @Override
            public Duration step() {
                return Duration.parse(properties.get("prometheus.step"));
            }

            @Override
            public Duration scrapeTimeout() {
                String timeout = properties.get("prometheus.scrape-timeout");
                return timeout != null ? Duration.parse(timeout) : super.scrapeTimeout();
            }

            @Override
            public boolean descriptions() {
                return Boolean.parseBoolean(properties.getOrDefault("prometheus.descriptions", "false"));
            }
        };

        return new PrometheusMeterRegistry(customConfig, clock);
    }
    // */

    @Bean
    public Clock clock() {
        return Clock.SYSTEM;
    }
    
    /*
        4. Composite Registry (Multiple Systems)        
    */
    //@Bean
    public MeterRegistry compositeMeterRegistry() {
        CompositeMeterRegistry composite = new CompositeMeterRegistry();
        // Add other registries here
        // composite.add(new PrometheusMeterRegistry(...));
        return composite;
    }    
}
