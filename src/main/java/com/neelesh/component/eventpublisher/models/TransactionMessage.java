package com.neelesh.component.eventpublisher.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionMessage {

    private Long transactionId;
    private Event event;
    private Double amount;
    private Status status;

    public enum Event {
        WITHDRAW, DEPOSIT
    }

    public enum Status {
        SUBMITTED, STARTED, PENDING, FINISHED, TERMINATED
    }
}
