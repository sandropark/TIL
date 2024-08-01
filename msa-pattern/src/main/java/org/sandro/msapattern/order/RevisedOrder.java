package org.sandro.msapattern.order;

public class RevisedOrder {
    private final Order order;
    private final LineItemQuantityChange change;

    public RevisedOrder(Order order, LineItemQuantityChange change) {
        this.order = order;
        this.change = change;
    }

    public Order getOrder() {
        return order;
    }

    public LineItemQuantityChange getChange() {
        return change;
    }
}
