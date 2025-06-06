/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.entity;

import com.spring5.type.VehicleUpdateStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //it includes @Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor 
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class vehicleUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private VehicleUpdateStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String errorLog;
        
}
