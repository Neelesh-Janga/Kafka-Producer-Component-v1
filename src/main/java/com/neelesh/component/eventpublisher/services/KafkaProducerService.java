package com.neelesh.component.eventpublisher.services;

import com.neelesh.component.eventpublisher.models.TransactionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
    public KafkaProducerService(KafkaTemplate<UUID, TransactionMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topicName, UUID key, TransactionMessage transactionMessage){

        LOGGER.info(String.format("Entered into send method with topicName %s, key %s & transactionMessage %s",
                topicName, key.toString(), transactionMessage.toString()));
        var future = kafkaTemplate.send(topicName, key, transactionMessage);
        // Callback function after send/produce event triggered to Kafka topic
        future.whenComplete((sendResult, exception) -> {
            if (exception != null){
                LOGGER.error("Exception encountered : " + exception.getMessage());
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }

            LOGGER.info("The id is : " + transactionMessage.getTransactionId()
                    + " Transaction status to Kafka topic: " + transactionMessage.getStatus());
        });
    }
}
