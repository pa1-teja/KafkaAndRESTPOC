package com.example.restTemplateMicroService1.demo.ThymleafControllers;


import com.example.restTemplateMicroService1.demo.POJO.Message;
import com.example.restTemplateMicroService1.demo.Services.KafkaProducerService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class KafkaMsgProducerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/producer")
    public String getPage(Model model){
        model.addAttribute("message", new Message());
        return "sender";
    }

    @PostMapping("/send")
    public String sendMessage(Message message, Model model) {

        producerService.sendMessage("dddd message","pa1Topic");

        model.addAttribute("response", "sent successfully");
        return "sender";
    }
}
