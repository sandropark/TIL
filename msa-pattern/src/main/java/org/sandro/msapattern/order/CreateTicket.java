package org.sandro.msapattern.order;

import io.eventuate.tram.commands.common.Command;

public class CreateTicket implements Command {
    public CreateTicket(Long restaurantId, Long orderId, Object ticketDetails) {

    }
}
