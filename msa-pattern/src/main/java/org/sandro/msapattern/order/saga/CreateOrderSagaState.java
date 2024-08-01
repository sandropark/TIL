package org.sandro.msapattern.order.saga;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sandro.msapattern.order.*;
import org.sandro.msapattern.order.command.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Slf4j
@NoArgsConstructor
public class CreateOrderSagaState {
    @Setter private Long orderId;
    private OrderDetails orderDetails;
    @Setter private long ticketId;

    public CreateOrderSagaState(Long orderId, OrderDetails orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    CreateTicket makeCreateTicketCommand() {
        return new CreateTicket(getOrderId(), makeTicketDetails(getOrderDetails()), getOrderDetails().getRestaurantId());
    }

    private TicketDetails makeTicketDetails(OrderDetails orderDetails) {
        // TODO FIXME
        return new TicketDetails(makeTicketLineItems(orderDetails.getLineItems()));
    }

    private List<TicketLineItem> makeTicketLineItems(List<OrderLineItem> lineItems) {
        return lineItems.stream().map(this::makeTicketLineItem).collect(toList());
    }

    private TicketLineItem makeTicketLineItem(OrderLineItem orderLineItem) {
        return new TicketLineItem(orderLineItem.getQuantity(), orderLineItem.getMenuItemId(), orderLineItem.getName());
    }

    void handleCreateTicketReply(CreateTicketReply reply) {
        log.debug("getTicketId {}", reply.getTicketId());
        setTicketId(reply.getTicketId());
    }

    CancelCreateTicket makeCancelCreateTicketCommand() {
        return new CancelCreateTicket(getOrderId());
    }

    RejectOrderCommand makeRejectOrderCommand() {
        return new RejectOrderCommand(getOrderId());
    }

    ValidateOrderByConsumer makeValidateOrderByConsumerCommand() {
        return new ValidateOrderByConsumer(getOrderDetails().getConsumerId(), getOrderId(), getOrderDetails().getOrderTotal());
    }

    AuthorizeCommand makeAuthorizeCommand() {
        return new AuthorizeCommand(getOrderDetails().getConsumerId(), getOrderId(), getOrderDetails().getOrderTotal());
    }

    ApproveOrderCommand makeApproveOrderCommand() {
        return new ApproveOrderCommand(getOrderId());
    }

    ConfirmCreateTicket makeConfirmCreateTicketCommand() {
        return new ConfirmCreateTicket(getTicketId());

    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}