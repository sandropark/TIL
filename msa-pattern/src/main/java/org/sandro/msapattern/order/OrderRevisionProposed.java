package org.sandro.msapattern.order;

public record OrderRevisionProposed(OrderRevision orderRevision, Money currentOrderTotal, Money newOrderTotal) implements OrderDomainEvent {
}