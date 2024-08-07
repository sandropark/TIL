package org.sandro.msapattern.order.proxy;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.sandro.msapattern.order.CreateTicketReply;
import org.sandro.msapattern.order.channel.KitchenServiceChannels;
import org.sandro.msapattern.order.command.CancelCreateTicket;
import org.sandro.msapattern.order.command.ConfirmCreateTicket;
import org.sandro.msapattern.order.command.CreateTicket;

public class KitchenServiceProxy {
    public final CommandEndpoint<CreateTicket> create = CommandEndpointBuilder
            .forCommand(CreateTicket.class)
            .withChannel(KitchenServiceChannels.kitchenServiceChannel)
            .withReply(CreateTicketReply.class)
            .build();

    public final CommandEndpoint<ConfirmCreateTicket> confirmCreate = CommandEndpointBuilder
            .forCommand(ConfirmCreateTicket.class)
            .withChannel(KitchenServiceChannels.kitchenServiceChannel)
            .withReply(Success.class)
            .build();

    public final CommandEndpoint<CancelCreateTicket> cancel = CommandEndpointBuilder
            .forCommand(CancelCreateTicket.class)
            .withChannel(KitchenServiceChannels.kitchenServiceChannel)
            .withReply(Success.class)
            .build();
}
