/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class OrderService {
    
    @Autowired
    private MBassador<Object> bus;
    
    public void createOrder(String orderId) {
        // Business logic...
        bus.publish(new OrderCreatedEvent(orderId));
    }    
    
    public void createOrder(String orderId, String email) {
        // Business logic...
        bus.publish(new OrderCreatedEvent(orderId));
    }    

    @Handler
    public void handle(UpdateInventoryCommand cmd) {
        // Update database...
        bus.publish(new InventoryUpdatedEvent(cmd.getProductId(), cmd.getNewQuantity()));
    }       
}
