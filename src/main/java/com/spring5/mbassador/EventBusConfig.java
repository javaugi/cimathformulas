/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author javaugi
 */
@Configuration
public class EventBusConfig {

    @Bean
    public MBassador<Object> highPerfBus() {
        MBassador<Object> highPerfBus = new MBassador<>(
                new BusConfiguration()
                        .setProperty(IBusConfiguration.Properties.BusId, "high-perf")
                        .addFeature(Feature.AsynchronousMessageDispatch.Default())
                        .addFeature(Feature.SyncPubSub.Default())
                        .addFeature(Feature.AsynchronousHandlerInvocation.Default())
        );

        return highPerfBus;
    }
    
    @Bean
    public GlobalErrorHandler pubErrorHandler() {
        return new GlobalErrorHandler();
    }

    @Bean
    public MBassador<Object> eventBus() {
        return new MBassador<>(new BusConfiguration()                
                .setProperty(IBusConfiguration.Properties.BusId, "perf")
                .addFeature(Feature.AsynchronousMessageDispatch.Default())
                .addFeature(Feature.AsynchronousHandlerInvocation.Default())
                .addFeature(Feature.SyncPubSub.Default())
                .setProperty(IBusConfiguration.Properties.BusId, "main-bus")
        );
    }
}
