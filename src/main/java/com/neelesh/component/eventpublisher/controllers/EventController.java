package com.neelesh.component.eventpublisher.controllers;

import com.neelesh.component.eventpublisher.models.TransactionMessage;
import com.neelesh.component.eventpublisher.services.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("v1/")
@Slf4j
public class EventController {

    private final KafkaProducerService kafkaProducerService;

    public EventController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/event")
    public ResponseEntity<String> event(@RequestBody TransactionMessage transactionMessage){
        UUID uuid = UUID.randomUUID();
        log.info("We received the transaction with the key: " + uuid);
        kafkaProducerService.send("transaction-topic", uuid, transactionMessage);

        return ResponseEntity.ok("Sent");
    }
}
