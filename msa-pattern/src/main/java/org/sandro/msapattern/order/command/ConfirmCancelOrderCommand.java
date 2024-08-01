package org.sandro.msapattern.order.command;

public class ConfirmCancelOrderCommand extends OrderCommand {

    private ConfirmCancelOrderCommand() {
    }

    public ConfirmCancelOrderCommand(long orderId) {
        super(orderId);
    }
}