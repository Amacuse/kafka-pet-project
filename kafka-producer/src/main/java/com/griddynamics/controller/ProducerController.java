package com.griddynamics.controller;

import com.griddynamics.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private static final Logger logger = LogManager.getLogger(ProducerController.class);

    private static final String START_PRODUCING_RESPONSE_MESSAGE = "Producing has been started successfully";
    private static final String STOP_PRODUCING_RESPONSE_MESSAGE = "Producing has been stopped successfully";

    @Autowired
    private MessageService messageService;
    @Autowired
    private Executor executor;

    @GetMapping("/start")
    public String start() {
        logger.info("Start producing request is received");
        executor.execute(() -> messageService.startConsequentMessage());
        return START_PRODUCING_RESPONSE_MESSAGE;
    }

    @GetMapping("/status/{topic}")
    public String status(@PathVariable String topic) {
        logger.info("Topic status {} request is received", topic);
        return String.format("The topic %s status is %s", topic, "Ok");
    }

    @GetMapping("/stop")
    public String stop() {
        logger.info("Stop producing request is received");
        executor.execute(() -> messageService.stopConsequentMessage());
        return STOP_PRODUCING_RESPONSE_MESSAGE;
    }
}
