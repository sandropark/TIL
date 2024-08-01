package org.sandro.msapattern.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviseOrderSagaData {
    private OrderRevision orderRevision;
    private Long orderId;
    private Long expectedVersion;
    private long restaurantId;
    private Money revisedOrderTotal;
    private long consumerId;

    public ReviseOrderSagaData(long consumerId, Long orderId, Long expectedVersion, OrderRevision orderRevision) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.expectedVersion = expectedVersion;
        this.orderRevision = orderRevision;
    }
}
