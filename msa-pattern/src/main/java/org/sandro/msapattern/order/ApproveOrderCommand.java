package org.sandro.msapattern.order;

import io.eventuate.tram.commands.common.Command;

public record ApproveOrderCommand(Long orderId) implements Command {
}
