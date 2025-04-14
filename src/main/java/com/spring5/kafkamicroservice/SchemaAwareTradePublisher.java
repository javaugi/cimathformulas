/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchemaAwareTradePublisher {
    //Schema-Aware Producer
    @Autowired
    private KafkaTemplate<String, GenericRecord> avroKafkaTemplate;

    public void publishTradeEvent(Trade trade) {
        TradeEvent event = TradingEventPublisher.createTradeEventFromTrade(trade);

        avroKafkaTemplate.send("trading.trade.events", String.valueOf(trade.getId()), getGenericRecord(event));
    }
    
    public static GenericRecord getGenericRecord(TradeEvent event) {
        return new GenericRecord(event);
    }
    
}
