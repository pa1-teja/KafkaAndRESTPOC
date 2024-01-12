package com.example.restTemplateMicroService1.demo.Controllers;

import com.example.restTemplateMicroService1.demo.Services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {
    @Autowired
    private KafkaProducerService producerService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessageToKafka(@RequestParam("message") String message,
                                                     @RequestParam("topic") String topic) {
        producerService.sendMessage(message, topic);
        return ResponseEntity.ok("Message sent to Kafka topic: " + topic);
    }


    @PostMapping("/receive")
    public ResponseEntity<String> receiveMessage(@RequestBody String message) {
        kafkaTemplate.send("pa1Topic", message);
        return ResponseEntity.ok("Message sent to Kafka");
    }
}

