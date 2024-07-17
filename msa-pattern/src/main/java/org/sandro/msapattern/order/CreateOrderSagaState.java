package org.sandro.msapattern.order;

import io.eventuate.tram.commands.common.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class CreateOrderSagaState {
    @Getter
    private Long orderId;
    private OrderDetails orderDetails;
    private long ticketId;

    public CreateOrderSagaState(Long id, OrderDetails orderDetails) {
        this.orderId = id;
        this.orderDetails = orderDetails;
    }

    public CreateTicket makeCreateTicketCommand() {
        return new CreateTicket(orderDetails.restaurantId(), orderId, makeTicketDetails(orderDetails));
    }

    public void handleCreateTicketReply(CreateTicketReply reply) {
        log.debug("ticketId = {}", reply.getTicketId());
        this.ticketId = reply.getTicketId();
    }

    public CancelCreateTicket makeCancelCreateTicketCommand() {
        return new CancelCreateTicket(orderId);
    }

    private Object makeTicketDetails(OrderDetails orderDetails) {
        return null;
    }

    public Command makeRejectOrderCommand() {
        return null;
    }

    public Command makeVaildateOrderByConsumerCommand() {
        return null;
    }
}
