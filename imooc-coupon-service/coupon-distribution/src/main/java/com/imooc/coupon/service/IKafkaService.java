package com.example.coupon.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Kafka-related service interface definitions
 */
public interface IKafkaService {

    /**
     * Consumer coupons Kafka messages
     * @param record {@link ConsumerRecord}
     * */
    void consumeCouponKafkaMessage(ConsumerRecord<?, ?> record);
}
