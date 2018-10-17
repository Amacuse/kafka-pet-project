package com.griddynamics.service;

import com.google.common.base.Throwables;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Service
public class MessageService {
    private static final Logger logger = LogManager.getLogger(MessageService.class);

    private static final AtomicInteger KEY = new AtomicInteger(1);
    private volatile boolean start = false;

    @Value("${kafka.producer.topic.name}")
    private String topic;
    @Autowired
    private Supplier<Producer<Integer, String>> producerSupplier;

    public void startConsequentMessage() {
        logger.info("Start sending messages to the topic=[{}]", topic);
        start = true;
        while (start) {
            String message = KEY + " message";
            logger.info("Sending a message=[{}]", message);
            producerSupplier.get().send(new ProducerRecord<>(topic, KEY.getAndIncrement(), message));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Throwables.propagate(e);
            }
        }
    }

    public void stopConsequentMessage() {
        start = false;
    }
}
