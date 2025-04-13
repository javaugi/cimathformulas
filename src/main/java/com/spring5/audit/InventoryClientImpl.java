/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;

@Component
public class InventoryClientImpl implements InventoryClient {

    @Override
    public CompletableFuture<Boolean> reserveItems(String orderId, List<Order> items) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate API call / DB operation
            try {
                Thread.sleep(500); // Simulate delay
                // Here you'd check inventory, update DB, etc.
                return true; // or false if failed
            } catch (InterruptedException e) {
                throw new RuntimeException("Reservation failed", e);
            }
        });
    }
    
    @Override
    public void cancelReservation(String orderId) {
        
    }
}