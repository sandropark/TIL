package org.sandro.msapattern.order.proxy;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.sandro.msapattern.order.channel.OrderServiceChannels;
import org.sandro.msapattern.order.command.ApproveOrderCommand;
import org.sandro.msapattern.order.command.RejectOrderCommand;

public class OrderServiceProxy {
    public final CommandEndpoint<RejectOrderCommand> reject = CommandEndpointBuilder
            .forCommand(RejectOrderCommand.class)
            .withChannel(OrderServiceChannels.orderServiceChannel)
            .withReply(Success.class)
            .build();

    public final CommandEndpoint<ApproveOrderCommand> approve = CommandEndpointBuilder
            .forCommand(ApproveOrderCommand.class)
            .withChannel(OrderServiceChannels.orderServiceChannel)
            .withReply(Success.class)
            .build();
}