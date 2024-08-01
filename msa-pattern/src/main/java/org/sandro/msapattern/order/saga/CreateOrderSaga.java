package org.sandro.msapattern.order.saga;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import lombok.extern.slf4j.Slf4j;
import org.sandro.msapattern.order.CreateTicketReply;
import org.sandro.msapattern.order.proxy.AccountingServiceProxy;
import org.sandro.msapattern.order.proxy.ConsumerServiceProxy;
import org.sandro.msapattern.order.proxy.KitchenServiceProxy;
import org.sandro.msapattern.order.proxy.OrderServiceProxy;

// 오케스트레이터
@Slf4j
public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaState> {
    private final SagaDefinition<CreateOrderSagaState> sagaDefinition;

    public CreateOrderSaga(OrderServiceProxy orderService, ConsumerServiceProxy consumerService, KitchenServiceProxy kitchenService,
                           AccountingServiceProxy accountingService) {
        this.sagaDefinition = step().withCompensation(orderService.reject, CreateOrderSagaState::makeRejectOrderCommand)  // 보상 트랜잭션
                .step().invokeParticipant(consumerService.validateOrder, CreateOrderSagaState::makeValidateOrderByConsumerCommand) // 포워드 트랜잭션

                .step()
                // makeCreateTicketCommand로 createTicketCommand를 생성 후 kitchenService.create 채널로 전달
                .invokeParticipant(kitchenService.create, CreateOrderSagaState::makeCreateTicketCommand)
                // onReply를 호출해서 주방 서비스로부터 성공 응답을 받으면 handleCreateTicketReply 를 호출
                // 주방 서비스가 반환한 ticketId를 state에 저장
                .onReply(CreateTicketReply.class, CreateOrderSagaState::handleCreateTicketReply)
                // onReply를 호출해서 주방 서비스로부터 실패 응답을 받으면 makeCancelCreateTicketCommand 를 호출
                // cancelCreateTicketCommand를 생성 후 kitchenService.cancel 채널로 전달
                .withCompensation(kitchenService.cancel, CreateOrderSagaState::makeCancelCreateTicketCommand)  // 보상 트랜잭션

                .step().invokeParticipant(accountingService.authorize, CreateOrderSagaState::makeAuthorizeCommand)
                .step().invokeParticipant(kitchenService.confirmCreate, CreateOrderSagaState::makeConfirmCreateTicketCommand)
                .step().invokeParticipant(orderService.approve, CreateOrderSagaState::makeApproveOrderCommand)
                .build();
    }

    @Override
    public SagaDefinition<CreateOrderSagaState> getSagaDefinition() {
        return sagaDefinition;
    }

}