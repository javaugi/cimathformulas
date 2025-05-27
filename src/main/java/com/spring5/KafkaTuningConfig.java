/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaTuningConfig {
    
    @Bean
    public ProducerFactory<String, Object> tunedProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384 * 4); // 64KB
        config.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        config.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "zstd");
        config.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432 * 2); // 64MB
        return new DefaultKafkaProducerFactory<>(config);
    }
    
    @Bean
    public ConsumerFactory<String, Object> tunedConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 16384); // 16KB
        config.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        return new DefaultKafkaConsumerFactory<>(config);
    }
} 

/*
Why Choose Kafka Over Others (e.g., RabbitMQ, ActiveMQ, JMS, Redis Pub/Sub)?
    Criteria            Kafka                                       Other Messaging Systems (e.g., RabbitMQ)
    Throughput          Very high (millions of messages/sec)        Moderate
    Message Durability	Yes, persisted to disk with replication     Often in-memory or transient
    Replayability	Yes, consumers can re-read old messages     Not supported or difficult
    Scalability         Horizontally scalable                       Limited or requires clustering
    Partitioning	Yes, native partitioning for parallelism    Limited
    Stream Processing	Kafka Streams, ksqlDB                       Not available

🚀 Core Advantages of Kafka
High Throughput & Low Latency
    Kafka can handle millions of events per second, even with modest hardware. It’s optimized for batch and stream processing.
Durability and Persistence
    Messages are written to disk and replicated across brokers. This ensures data integrity and fault tolerance.
Scalability
    Kafka supports horizontal scaling via topic partitioning. Producers and consumers can scale independently.
Replayable Event Log
    Kafka keeps messages for a configured time (e.g., 7 days). Consumers can re-read messages from any offset — useful for data reprocessing.
Exactly-Once and At-Least-Once Semantics
    Kafka supports at-least-once delivery by default and can be configured for exactly-once processing in certain workflows.
Decoupling of Services
    Kafka enables loose coupling: producers don’t know who the consumers are, and consumers can join/leave independently.
Integration Ecosystem
    Works well with Apache Spark, Flink, Hadoop, Debezium, and has a powerful Kafka Connect ecosystem for integrating with databases, storage, etc.
Built-in Monitoring
    Kafka exposes detailed metrics via JMX, and integrates easily with Prometheus + Grafana for observability.

🛠️ Example Use Cases
    Real-time fraud detection (e.g., in banking).
    Audit logs for regulatory compliance.
    Website activity tracking (like LinkedIn, Netflix).
    Event sourcing in microservices architecture.
    Stream processing pipelines.

🔁 Kafka vs RabbitMQ (Quick Summary)
Feature             Kafka                       RabbitMQ
Storage Model       Log-based                   Queue-based
Message Retention   Time-based                  Until consumed
Ordering            Partition-order guaranteed	No strict ordering
Use Case            Event streaming             Task queueing
Replay Support      Yes                         No
*/