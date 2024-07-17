package org.sandro.msapattern.order;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.ResultWithEvents;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {
    private final SagaManager<CreateOrderSagaState> createOrderSagaManager;
    private final OrderRepository orderRepository;
    private final DomainEventPublisher eventPublisher;

    public Order createOrder(OrderDetails orderDetails) {
        ResultWithEvents<Order> orderAndEvents = Order.createOrder();
        Order order = orderAndEvents.result;
        orderRepository.save(order);

        eventPublisher.publish(Order.class, order.getId(), orderAndEvents.events);

        CreateOrderSagaState data = new CreateOrderSagaState(order.getId(), orderDetails);
        createOrderSagaManager.create(data, Order.class, order.getId());

        return order;
    }

    public void approveOrder(Long orderId) {

    }

    public void rejectOrder(Long orderId) {

    }
}
