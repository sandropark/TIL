package org.sandro.msapattern.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super("Order not found" + orderId);
    }
}
