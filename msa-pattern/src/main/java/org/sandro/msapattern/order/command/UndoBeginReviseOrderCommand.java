package org.sandro.msapattern.order.command;

public class UndoBeginReviseOrderCommand extends OrderCommand {

    protected UndoBeginReviseOrderCommand() {
    }

    public UndoBeginReviseOrderCommand(long orderId) {
        super(orderId);
    }
}
