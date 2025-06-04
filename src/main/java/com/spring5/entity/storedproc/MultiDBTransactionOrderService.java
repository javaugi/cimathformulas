/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.entity.storedproc;

import com.spring5.audit.Order;
import com.spring5.audit.OrderRepository;
import com.spring5.audit.OrderRequest;
import com.spring5.entity.Inventory;
import com.spring5.kafkamicroservice.Payment;
import com.spring5.kafkamicroservice.PaymentRepository;
import com.spring5.repository.InventoryRepository;
import com.spring5.repository.ProductRepository;
import com.spring5.validator.InventoryException;
import com.spring5.validator.PaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MultiDBTransactionOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final PaymentRepository paymentRepository;
    
    @Transactional
    public Order processOrder(OrderRequest request) {
        // 1. Check inventory
        //Inventory inventory  = inventoryRepository.findById(request.getItemId()).get()
        Inventory inventory = inventoryRepository.findById(request.getItemId())
            .orElseThrow(() -> new InventoryException("Item not found"));
        
        if (inventory.getQuantity() < request.getQuantity()) {
            throw new InventoryException("Not enough stock");
        }
        
        // 2. Process payment
        Payment payment = paymentRepository.processPayment(
            request.getPaymentDetails());
        
        if (!payment.isSuccess()) {
            throw new PaymentException("Payment failed");
        }
        
        // 3. Create order
        Order order = new Order(request);
        orderRepository.save(order);
        
        // 4. Update inventory
        inventory.setQuantity(inventory.getQuantity() - request.getQuantity());
        inventoryRepository.save(inventory);
        
        return order;
    }    
}
