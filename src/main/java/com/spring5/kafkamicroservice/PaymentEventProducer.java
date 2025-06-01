/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import io.github.resilience4j.retry.annotation.Retry;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentEventProducer {

    private static final String PAYMENT_REQUESTS_TOPIC = "payment-requests";
    private static final Logger log = LoggerFactory.getLogger(PaymentEventProducer.class);
    
    private final @Qualifier("paymentKafkaTemplate") KafkaTemplate<String, PaymentRequestEvent> kafkaTemplate;
    
    @Retry(name = "kafkaPublish", fallbackMethod = "publishFailed")
    public void publishPaymentRequest(PaymentRequestEvent event) {
        CompletableFuture<SendResult<String, PaymentRequestEvent>> future = 
            kafkaTemplate.send(PAYMENT_REQUESTS_TOPIC, event.paymentId(), event);
        
        future.thenAccept(result -> log.info("Published payment request {}", event.paymentId()))
            .exceptionally(ex -> {
                log.error("Failed to publish payment {}", event.paymentId(), ex);
                return null; // Or a default value
            });
    }
    
    public void publishFailed(PaymentRequestEvent event, Exception ex) {
        log.error("All retries exhausted for payment {}", event.paymentId(), ex);
        // Store in DB for later retry or manual processing
    }
    
    public void publishPaymentCompleted(PaymentResult result) {
        
    }
    public void publishPaymentFailed(PaymentRequestEvent event, String error) {
        
    }
    
    
}