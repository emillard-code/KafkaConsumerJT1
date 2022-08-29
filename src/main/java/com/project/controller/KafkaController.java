package com.project.controller;

import com.project.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KafkaController {

    List<String> messages = new ArrayList<>();
    User userFromTopic = null;

    @GetMapping("/consumeStringMessage")
    public List<String> consumeMsg() {

        return messages;

    }

    @GetMapping("/consumeJsonMessage")
    public User consumeJsonMessage() {

        return userFromTopic;

    }

    @KafkaListener(groupId = "kafka-topic-1", topics = "kafka-topic", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data) {

        messages.add(data);
        return messages;
        
    }

    @KafkaListener(groupId = "kafka-topic-2", topics = "kafka-topic", containerFactory = "userKafkaListenerContainerFactory")
    public User getJsonMsgFromTopic(User user) {

        userFromTopic = user;
        return userFromTopic;

    }

}