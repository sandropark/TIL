package org.sandro.msapattern.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class CancelOrderSagaData {
    private Long orderId;
    @Setter private String reverseRequestId;
    @Setter private long restaurantId;
    private long consumerId;
    private Money orderTotal;

    public CancelOrderSagaData(long consumerId, long orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }
}