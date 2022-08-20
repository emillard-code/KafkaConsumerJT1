package com.project.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KafkaController {

    List<String> messages = new ArrayList<>();

    @GetMapping("/consumeStringMessage")
    public List<String> consumeMsg() {

        return messages;
        
    }

    @KafkaListener(groupId = "javatechie-1", topics = "javatechie", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data) {

        messages.add(data);
        return messages;
        
    }

}