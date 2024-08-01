package org.sandro.msapattern.order;

public class BeginReviseOrderReply {
    private Money revisedOrderTotal;

    public BeginReviseOrderReply(Money revisedOrderTotal) {
        this.revisedOrderTotal = revisedOrderTotal;
    }

    public BeginReviseOrderReply() {
    }

    public Money getRevisedOrderTotal() {
        return revisedOrderTotal;
    }

    public void setRevisedOrderTotal(Money revisedOrderTotal) {
        this.revisedOrderTotal = revisedOrderTotal;
    }
}
