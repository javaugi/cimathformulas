/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdyenPaymentRepository extends JpaRepository<AdyenPayment, Long> {
    boolean existsByIdAndStatusNot(String paymentId, AdyenPaymentStatus status);
    boolean existsByIdempotencyKey(String ddempotencyKey);
    List<AdyenPayment> findByStatus(AdyenPaymentStatus status);
}
