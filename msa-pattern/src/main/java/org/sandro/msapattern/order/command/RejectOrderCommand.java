package org.sandro.msapattern.order.command;

import io.eventuate.tram.commands.common.Command;

public record RejectOrderCommand(Long orderId) implements Command {
}
