/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.engio.mbassy.bus.MBassador;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistributedEventPublisher {

    private final MBassador<Object> eventBus;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    
    @PostConstruct
    public void setupForwarding() {
        // Forward all events to Kafka
        /*
        eventBus.subscribe((Object event) -> {
            try {
                String topic = event.getClass().getSimpleName();
                String payload = objectMapper.writeValueAsString(event);
                kafkaTemplate.send(topic, payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Event serialization failed", e);
            }
        });
        // */
    }
}