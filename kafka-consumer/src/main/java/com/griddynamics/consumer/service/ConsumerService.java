package com.griddynamics.consumer.service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.function.Supplier;

@Service
public class ConsumerService {
    private static final Logger logger = LogManager.getLogger(ConsumerService.class);

    private static final Duration POOL_TIMEOUT = Duration.ofMillis(100L);
    private volatile boolean start = false;

    @Value("${kafka.consumer.topic.name}")
    private String topic;

    @Autowired
    private Supplier<Consumer<Integer, String>> consumerSupplier;

    public void startConsuming() {
        Consumer<Integer, String> consumer = consumerSupplier.get();
        consumer.subscribe(Collections.singleton(topic));
        start = true;
        while (start) {
            ConsumerRecords<Integer, String> records = consumer.poll(POOL_TIMEOUT);
            for (ConsumerRecord<Integer, String> record : records) {
                logger.info("Received record for topic=[{}], partition=[{}], offset=[{}]",
                        record.topic(),
                        record.partition(),
                        record.offset());

                logger.info("Message: key=[{}], value=[{}]", record.key(), record.value());
            }
        }
    }

    public void stopConsuming() {
        start = false;
    }
}
