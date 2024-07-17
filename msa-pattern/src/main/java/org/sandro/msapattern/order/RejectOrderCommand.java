package org.sandro.msapattern.order;

import io.eventuate.tram.commands.common.Command;

public record RejectOrderCommand(Long orderId) implements Command {
}
