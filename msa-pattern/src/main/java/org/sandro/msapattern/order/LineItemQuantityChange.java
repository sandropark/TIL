package org.sandro.msapattern.order;

public record LineItemQuantityChange(Money currentOrderTotal, Money newOrderTotal, Money delta) {
}