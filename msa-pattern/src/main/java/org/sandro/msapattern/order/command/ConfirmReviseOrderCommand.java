package org.sandro.msapattern.order.command;

import org.sandro.msapattern.order.OrderRevision;

public class ConfirmReviseOrderCommand extends OrderCommand {

    private ConfirmReviseOrderCommand() {
    }

    public ConfirmReviseOrderCommand(long orderId, OrderRevision revision) {
        super(orderId);
        this.revision = revision;
    }

    private OrderRevision revision;

    public OrderRevision getRevision() {
        return revision;
    }
}
