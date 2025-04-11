/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author javaugi
 */
@Getter
@Setter
@ToString
public class OperationStats {

    private final AtomicLong count = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();
    private final AtomicLong minTime = new AtomicLong(Long.MAX_VALUE);
    private final AtomicLong maxTime = new AtomicLong(Long.MIN_VALUE);

    public OperationStats(long initialDuration) {
        recordDuration(initialDuration);
    }

    public void recordDuration(long duration) {
        count.incrementAndGet();
        totalTime.addAndGet(duration);
        minTime.accumulateAndGet(duration, Math::min);
        maxTime.accumulateAndGet(duration, Math::max);
    }

    public double getAverageTime() {
        return totalTime.get() / (double) count.get();
    }
}
