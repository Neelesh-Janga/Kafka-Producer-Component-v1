package com.neelesh.component.eventpublisher.DTO;

import lombok.*;
import com.neelesh.component.eventpublisher.utils.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class TransactionMessageDTO {
    private Long transactionId;
    private Event event;
    private Double amount;
    private Status status;
}
