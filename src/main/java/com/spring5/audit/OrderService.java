/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.spring5.mbassador.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author javaugi
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final TransactionalEventPublisher eventPublisher;
    
    /*
    public OrderService(OrderRepository orderRepository, TransactionalEventPublisher eventPublisher ) {
        this.orderRepository = orderRepository;
        this.eventPublisher = eventPublisher;
    }
    // */

    @Transactional
    public void createOrder(Order order) {
        orderRepository.save(order);
        // Event will only publish if transaction succeeds
        eventPublisher.publishAfterCommit(new OrderCreatedEvent("" + order.getId()));
    }    
}
