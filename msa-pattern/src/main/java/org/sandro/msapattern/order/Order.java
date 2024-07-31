package org.sandro.msapattern.order;

import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.sandro.msapattern.exception.OrderMinimumNotMetException;
import org.sandro.msapattern.exception.UnsupportedStateTransitionException;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.sandro.msapattern.order.OrderState.*;

@NoArgsConstructor
@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class Order {
    public static ResultWithDomainEvents<Order, OrderDomainEvent>
    createOrder(long consumerId, Restaurant restaurant, List<OrderLineItem> orderLineItems) {
        Order order = new Order(consumerId, restaurant.getId(), orderLineItems);
        List<OrderDomainEvent> events = singletonList(new OrderCreatedEvent(
                new OrderDetails(consumerId, restaurant.getId(), orderLineItems,
                        order.getOrderTotal()),
                restaurant.getName()));
        return new ResultWithDomainEvents<>(order, events);
    }

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private Long consumerId;
    private Long restaurantId;

    @Embedded
    private OrderLineItems orderLineItems;

    @Embedded
    private DeliveryInformation deliveryInformation;

    @Embedded
    private PaymentInformation paymentInformation;

    @Embedded
    private Money orderMinimum = new Money(Integer.MAX_VALUE);

    public Order(long consumerId, long restaurantId, List<OrderLineItem> orderLineItems) {
        this.consumerId = consumerId;
        this.restaurantId = restaurantId;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.state = APPROVAL_PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Money getOrderTotal() {
        return orderLineItems.orderTotal();
    }

    public List<OrderDomainEvent> cancel() {
        switch (state) {
            case APPROVED:
                this.state = CANCEL_PENDING;
                return emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> undoPendingCancel() {
        switch (state) {
            case CANCEL_PENDING:
                this.state = APPROVED;
                return emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> noteCancelled() {
        switch (state) {
            case CANCEL_PENDING:
                this.state = CANCELLED;
                return singletonList(new OrderCancelled());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> noteApproved() {
        switch (state) {
            case APPROVAL_PENDING:
                this.state = APPROVED;
                return singletonList(new OrderAuthorized());
            default:
                throw new UnsupportedStateTransitionException(state);
        }

    }

    public List<OrderDomainEvent> noteRejected() {
        switch (state) {
            case APPROVAL_PENDING:
                this.state = REJECTED;
                return singletonList(new OrderRejected());

            default:
                throw new UnsupportedStateTransitionException(state);
        }

    }


    public List<OrderDomainEvent> noteReversingAuthorization() {
        return null;
    }

    public ResultWithDomainEvents<LineItemQuantityChange, OrderDomainEvent> revise(OrderRevision orderRevision) {
        switch (state) {

            case APPROVED:
                LineItemQuantityChange change = orderLineItems.lineItemQuantityChange(orderRevision);
                if (change.newOrderTotal.isGreaterThanOrEqual(orderMinimum)) {
                    throw new OrderMinimumNotMetException();
                }
                this.state = REVISION_PENDING;
                return new ResultWithDomainEvents<>(change, singletonList(new OrderRevisionProposed(orderRevision, change.currentOrderTotal, change.newOrderTotal)));

            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> rejectRevision() {
        switch (state) {
            case REVISION_PENDING:
                this.state = APPROVED;
                return emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> confirmRevision(OrderRevision orderRevision) {
        if (Objects.requireNonNull(state) == REVISION_PENDING) {
            LineItemQuantityChange licd = orderLineItems.lineItemQuantityChange(orderRevision);

            orderRevision.getDeliveryInformation().ifPresent(newDi -> this.deliveryInformation = newDi);

            if (!orderRevision.getRevisedLineItemQuantities().isEmpty()) {
                orderLineItems.updateLineItems(orderRevision);
            }

            this.state = APPROVED;
            return singletonList(new OrderRevised(orderRevision, licd.currentOrderTotal, licd.newOrderTotal));
        }
        throw new UnsupportedStateTransitionException(state);
    }


    public Long getVersion() {
        return version;
    }

    public List<OrderLineItem> getLineItems() {
        return orderLineItems.getLineItems();
    }

    public OrderState getState() {
        return state;
    }

    public long getRestaurantId() {
        return restaurantId;
    }


    public Long getConsumerId() {
        return consumerId;
    }
}
