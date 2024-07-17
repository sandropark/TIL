package org.sandro.msapattern.order;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.RequiredArgsConstructor;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

@RequiredArgsConstructor
public class OrderCommandHandlers {
    private final OrderService orderService;

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel("orderService")
                .onMessage(ApproveOrderCommand.class, this::approveOrder)
                .onMessage(RejectOrderCommand.class, this::rejectOrder)
                .build();
    }

    private Message approveOrder(CommandMessage<ApproveOrderCommand> cm) {
        Long orderId = cm.getCommand().orderId();
        orderService.approveOrder(orderId);
        return withSuccess();
    }

    private Message rejectOrder(CommandMessage<RejectOrderCommand> cm) {
        Long orderId = cm.getCommand().orderId();
        orderService.rejectOrder(orderId);
        return withSuccess();
    }
}
