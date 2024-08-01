package org.sandro.msapattern.order.command;

public class BeginCancelCommand extends OrderCommand {

    private BeginCancelCommand() {
    }

    public BeginCancelCommand(long orderId) {
        super(orderId);
    }
}
