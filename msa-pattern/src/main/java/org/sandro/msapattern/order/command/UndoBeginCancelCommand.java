package org.sandro.msapattern.order.command;

public class UndoBeginCancelCommand extends OrderCommand {
    public UndoBeginCancelCommand(long orderId) {
        super(orderId);
    }
}
