package com.example.restTemplateMicroService1.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Producer";
    }
}
