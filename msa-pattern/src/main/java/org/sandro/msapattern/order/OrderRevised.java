package org.sandro.msapattern.order;

public record OrderRevised(OrderRevision orderRevision, Money currentOrderTotal, Money newOrderTotal) implements OrderDomainEvent {
}