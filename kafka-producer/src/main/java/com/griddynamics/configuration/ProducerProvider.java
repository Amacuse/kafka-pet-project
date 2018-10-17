package com.griddynamics.configuration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class ProducerProvider implements Supplier<Producer<Integer, String>> {
    @Autowired
    private Environment environment;
    private Producer<Integer, String> producer;

    @PostConstruct
    public void init() {
        producer = new KafkaProducer<>(
                Arrays
                        .stream(KafkaProducerProperty.values())
                        .collect(Collectors.toMap(
                                KafkaProducerProperty::getKafkaPropertyName,
                                prop -> environment.getProperty(prop.getKafkaProducerPropertyName()))));
    }

    @PreDestroy
    public void tearDown() {
        producer.close();
    }

    @Override
    public Producer<Integer, String> get() {
        return producer;
    }
}
