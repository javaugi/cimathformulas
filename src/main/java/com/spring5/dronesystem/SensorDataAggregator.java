/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class SensorDataAggregator {

    private final ConcurrentMap<String, SensorData> latestData = new ConcurrentHashMap<>();
    private final BlockingQueue<SensorData> dataQueue = new LinkedBlockingQueue<>();
    private final AtomicLong processedCount = new AtomicLong(0);
    private final ExecutorService processingPool;
    private volatile boolean running = true;

    public SensorDataAggregator(int poolSize) {
        this.processingPool = Executors.newFixedThreadPool(poolSize);
        startProcessing();
    }

    public void addData(SensorData data) {
        dataQueue.offer(data);
    }

    private void startProcessing() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            processingPool.submit(() -> {
                while (running) {
                    try {
                        SensorData data = dataQueue.poll(100, TimeUnit.MILLISECONDS);
                        if (data != null) {
                            processData(data);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    private void processData(SensorData data) {
        latestData.put(data.getDroneId(), data);
        processedCount.incrementAndGet();
        // Additional processing logic here
    }

    public SensorData getLatestData(String droneId) {
        return latestData.get(droneId);
    }

    public Map<String, SensorData> getAllLatestData() {
        return new HashMap<>(latestData);
    }

    public long getProcessedCount() {
        return processedCount.get();
    }

    public void shutdown() {
        running = false;
        processingPool.shutdown();
        try {
            if (!processingPool.awaitTermination(5, TimeUnit.SECONDS)) {
                processingPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            processingPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
