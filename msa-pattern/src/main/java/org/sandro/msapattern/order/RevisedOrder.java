package org.sandro.msapattern.order;

public record RevisedOrder(Order order, LineItemQuantityChange change) {
}
