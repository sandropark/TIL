package org.sandro.msapattern.order.proxy;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.sandro.msapattern.order.channel.ConsumerServiceChannels;
import org.sandro.msapattern.order.command.ValidateOrderByConsumer;

public class ConsumerServiceProxy {
    public final CommandEndpoint<ValidateOrderByConsumer> validateOrder = CommandEndpointBuilder
            .forCommand(ValidateOrderByConsumer.class)
            .withChannel(ConsumerServiceChannels.consumerServiceChannel)
            .withReply(Success.class)
            .build();
}