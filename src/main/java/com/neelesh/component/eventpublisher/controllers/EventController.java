package com.neelesh.component.eventpublisher.controllers;

import com.neelesh.component.eventpublisher.DTO.TransactionMessageDTO;
import com.neelesh.component.eventpublisher.services.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("v1/event")
@Slf4j
public class EventController {

    private final KafkaProducerService kafkaProducerService;

    public EventController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> event(@RequestBody TransactionMessageDTO transactionMessageDTO){
        UUID uuid = UUID.randomUUID();
        log.info(String.format("We received the transaction with the key %s & transactionMessage %s",
                uuid, transactionMessageDTO.toString()));

        kafkaProducerService.send("transaction-topic", uuid, transactionMessageDTO);

        return ResponseEntity.ok("Event Sent Successfully");
    }
}
