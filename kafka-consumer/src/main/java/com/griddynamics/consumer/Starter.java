package com.griddynamics.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@PropertySource(value = {"classpath:kafka.consumer.properties"})
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }

    @Bean
    public Executor getControllerExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
}
