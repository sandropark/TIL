package org.sandro.msapattern.order.domain_event;

import org.sandro.msapattern.order.Money;
import org.sandro.msapattern.order.OrderRevision;

public record OrderRevisionProposed(OrderRevision orderRevision, Money currentOrderTotal, Money newOrderTotal) implements OrderDomainEvent {
}