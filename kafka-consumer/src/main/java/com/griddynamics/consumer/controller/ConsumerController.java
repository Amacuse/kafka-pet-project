package com.griddynamics.consumer.controller;

import com.griddynamics.consumer.service.ConsumerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    private static final Logger logger = LogManager.getLogger(ConsumerController.class);

    private static final String START_CONSUMING_RESPONSE_MESSAGE = "Consuming has been started successfully";
    private static final String STOP_CONSUMING_RESPONSE_MESSAGE = "Consuming has been stopped successfully";

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private Executor executor;

    @GetMapping("/start")
    public String start() {
        logger.info("START consuming request is received");
        executor.execute(() -> consumerService.startConsuming());
        return START_CONSUMING_RESPONSE_MESSAGE;
    }

    @GetMapping("/stop")
    public String stop() {
        logger.info("STOP consuming request is received");
        executor.execute(() -> consumerService.stopConsuming());
        return STOP_CONSUMING_RESPONSE_MESSAGE;
    }
}
