package com.example.restTemplateMicroService1.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ProcessedMessageController {

    private final Map<String, String> messageStore = new ConcurrentHashMap<>();

    @KafkaListener(topics = "pa1Topic", groupId = "1993")
    public void listen(String message) {

        String processedMessage = processMessage(message);

        String messageId = UUID.randomUUID().toString();
        messageStore.put(messageId, processedMessage);
    }

    private String processMessage(String message) {

        return "Processed: " + message;
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<String> getMessage(@PathVariable String id) {
        String message = messageStore.get(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
