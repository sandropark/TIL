package org.sandro.msapattern.order.saga;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import jakarta.annotation.PostConstruct;
import org.sandro.msapattern.order.BeginReviseOrderReply;
import org.sandro.msapattern.order.ReviseOrderSagaData;
import org.sandro.msapattern.order.channel.AccountingServiceChannels;
import org.sandro.msapattern.order.channel.KitchenServiceChannels;
import org.sandro.msapattern.order.channel.OrderServiceChannels;
import org.sandro.msapattern.order.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

public class ReviseOrderSaga implements SimpleSaga<ReviseOrderSagaData> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SagaDefinition<ReviseOrderSagaData> sagaDefinition;

    @PostConstruct
    public void initializeSagaDefinition() {
        sagaDefinition = step()
                .invokeParticipant(this::beginReviseOrder)
                .onReply(BeginReviseOrderReply.class, this::handleBeginReviseOrderReply)
                .withCompensation(this::undoBeginReviseOrder)
                .step()
                .invokeParticipant(this::beginReviseTicket)
                .withCompensation(this::undoBeginReviseTicket)
                .step()
                .invokeParticipant(this::reviseAuthorization)
                .step()
                .invokeParticipant(this::confirmTicketRevision)
                .step()
                .invokeParticipant(this::confirmOrderRevision)
                .build();
    }

    private void handleBeginReviseOrderReply(ReviseOrderSagaData data, BeginReviseOrderReply reply) {
        logger.info("ƒ order total: {}", reply.getRevisedOrderTotal());
        data.setRevisedOrderTotal(reply.getRevisedOrderTotal());
    }

    @Override
    public SagaDefinition<ReviseOrderSagaData> getSagaDefinition() {
        return sagaDefinition;
    }

    private CommandWithDestination confirmOrderRevision(ReviseOrderSagaData data) {
        return send(new ConfirmReviseOrderCommand(data.getOrderId(), data.getOrderRevision()))
                .to(OrderServiceChannels.orderServiceChannel)
                .build();
    }

    private CommandWithDestination confirmTicketRevision(ReviseOrderSagaData data) {
        return send(new ConfirmReviseTicketCommand(data.getRestaurantId(), data.getOrderId(), data.getOrderRevision().getRevisedLineItemQuantities()))
                .to(KitchenServiceChannels.kitchenServiceChannel)
                .build();
    }

    private CommandWithDestination reviseAuthorization(ReviseOrderSagaData data) {
        return send(new ReviseAuthorization(data.getConsumerId(), data.getOrderId(), data.getRevisedOrderTotal()))
                .to(AccountingServiceChannels.accountingServiceChannel)
                .build();
    }

    private CommandWithDestination undoBeginReviseTicket(ReviseOrderSagaData data) {
        return send(new UndoBeginReviseTicketCommand(data.getRestaurantId(), data.getOrderId()))
                .to(KitchenServiceChannels.kitchenServiceChannel)
                .build();
    }

    private CommandWithDestination beginReviseTicket(ReviseOrderSagaData data) {
        return send(new BeginReviseTicketCommand(data.getRestaurantId(), data.getOrderId(), data.getOrderRevision().getRevisedLineItemQuantities()))
                .to(KitchenServiceChannels.kitchenServiceChannel)
                .build();
    }

    private CommandWithDestination undoBeginReviseOrder(ReviseOrderSagaData data) {
        return send(new UndoBeginReviseOrderCommand(data.getOrderId()))
                .to(OrderServiceChannels.orderServiceChannel)
                .build();
    }

    private CommandWithDestination beginReviseOrder(ReviseOrderSagaData data) {
        return send(new BeginReviseOrderCommand(data.getOrderId(), data.getOrderRevision()))
                .to(OrderServiceChannels.orderServiceChannel)
                .build();
    }
}