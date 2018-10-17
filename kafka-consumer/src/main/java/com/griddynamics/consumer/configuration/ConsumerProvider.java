package com.griddynamics.consumer.configuration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class ConsumerProvider implements Supplier<Consumer<Integer, String>> {
    @Autowired
    private Environment environment;
    private Consumer<Integer, String> consumer;

    @PostConstruct
    private void init() {
        consumer = new KafkaConsumer<>(
                Arrays
                        .stream(KafkaConsumerProperty.values())
                        .collect(Collectors.toMap(
                                KafkaConsumerProperty::getKafkaPropertyName,
                                prop -> environment.getProperty(prop.getKafkaConsumerPropertyName()))));
    }

    @PreDestroy
    public void tearDown() {
        consumer.close();
    }

    @Override
    public Consumer<Integer, String> get() {
        return consumer;
    }
}
