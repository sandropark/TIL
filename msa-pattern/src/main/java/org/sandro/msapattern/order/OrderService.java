package org.sandro.msapattern.order;

import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.micrometer.core.instrument.MeterRegistry;
import org.sandro.msapattern.exception.InvalidMenuItemIdException;
import org.sandro.msapattern.exception.OrderNotFoundException;
import org.sandro.msapattern.exception.RestaurantNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Transactional
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final SagaManager<CreateOrderSagaState> createOrderSagaManager;
    private final SagaManager<CancelOrderSagaData> cancelOrderSagaManager;
    private final SagaManager<ReviseOrderSagaData> reviseOrderSagaManager;
    private final OrderDomainEventPublisher orderAggregateEventPublisher;
    private final Optional<MeterRegistry> meterRegistry;

    public OrderService(OrderRepository orderRepository, DomainEventPublisher eventPublisher, RestaurantRepository restaurantRepository,
                        SagaManager<CreateOrderSagaState> createOrderSagaManager, SagaManager<CancelOrderSagaData> cancelOrderSagaManager,
                        SagaManager<ReviseOrderSagaData> reviseOrderSagaManager, OrderDomainEventPublisher orderAggregateEventPublisher,
                        Optional<MeterRegistry> meterRegistry) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.createOrderSagaManager = createOrderSagaManager;
        this.cancelOrderSagaManager = cancelOrderSagaManager;
        this.reviseOrderSagaManager = reviseOrderSagaManager;
        this.orderAggregateEventPublisher = orderAggregateEventPublisher;
        this.meterRegistry = meterRegistry;
    }

    public Order createOrder(long consumerId, long restaurantId,
                             List<MenuItemIdAndQuantity> lineItems) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        List<OrderLineItem> orderLineItems = makeOrderLineItems(lineItems, restaurant);

        ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents =
                Order.createOrder(consumerId, restaurant, orderLineItems);

        Order order = orderAndEvents.result;
        orderRepository.save(order);

        orderAggregateEventPublisher.publish(order, orderAndEvents.events);

        OrderDetails orderDetails = new OrderDetails(consumerId, restaurantId, orderLineItems, order.getOrderTotal());

        CreateOrderSagaState data = new CreateOrderSagaState(order.getId(), orderDetails);
        createOrderSagaManager.create(data, Order.class, order.getId());

        meterRegistry.ifPresent(mr -> mr.counter("placed_orders").increment());

        return order;
    }


    private List<OrderLineItem> makeOrderLineItems(List<MenuItemIdAndQuantity> lineItems, Restaurant restaurant) {
        return lineItems.stream().map(li -> {
            MenuItem om = restaurant.findMenuItem(li.getMenuItemId()).orElseThrow(() -> new InvalidMenuItemIdException(li.getMenuItemId()));
            return new OrderLineItem(li.getMenuItemId(), om.getName(), om.getPrice(), li.getQuantity());
        }).collect(toList());
    }


    public Optional<Order> confirmChangeLineItemQuantity(Long orderId, OrderRevision orderRevision) {
        return orderRepository.findById(orderId).map(order -> {
            List<OrderDomainEvent> events = order.confirmRevision(orderRevision);
            orderAggregateEventPublisher.publish(order, events);
            return order;
        });
    }

    public void noteReversingAuthorization(Long orderId) {
        throw new UnsupportedOperationException();
    }

    public Order cancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        CancelOrderSagaData sagaData = new CancelOrderSagaData(order.getConsumerId(), orderId, order.getOrderTotal());
        cancelOrderSagaManager.create(sagaData);
        return order;
    }

    private Order updateOrder(long orderId, Function<Order, List<OrderDomainEvent>> updater) {
        return orderRepository.findById(orderId).map(order -> {
            orderAggregateEventPublisher.publish(order, updater.apply(order));
            return order;
        }).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void approveOrder(long orderId) {
        updateOrder(orderId, Order::noteApproved);
        meterRegistry.ifPresent(mr -> mr.counter("approved_orders").increment());
    }

    public void rejectOrder(long orderId) {
        updateOrder(orderId, Order::noteRejected);
        meterRegistry.ifPresent(mr -> mr.counter("rejected_orders").increment());
    }

    public void beginCancel(long orderId) {
        updateOrder(orderId, Order::cancel);
    }

    public void undoCancel(long orderId) {
        updateOrder(orderId, Order::undoPendingCancel);
    }

    public void confirmCancelled(long orderId) {
        updateOrder(orderId, Order::noteCancelled);
    }

    public Order reviseOrder(long orderId, OrderRevision orderRevision) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        ReviseOrderSagaData sagaData = new ReviseOrderSagaData(order.getConsumerId(), orderId, null, orderRevision);
        reviseOrderSagaManager.create(sagaData);
        return order;
    }

    public Optional<RevisedOrder> beginReviseOrder(long orderId, OrderRevision revision) {
        return orderRepository.findById(orderId).map(order -> {
            ResultWithDomainEvents<LineItemQuantityChange, OrderDomainEvent> result = order.revise(revision);
            orderAggregateEventPublisher.publish(order, result.events);
            return new RevisedOrder(order, result.result);
        });
    }

    public void undoPendingRevision(long orderId) {
        updateOrder(orderId, Order::rejectRevision);
    }

    public void confirmRevision(long orderId, OrderRevision revision) {
        updateOrder(orderId, order -> order.confirmRevision(revision));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void createMenu(long id, String name, RestaurantMenu menu) {
        Restaurant restaurant = new Restaurant(id, menu.getMenuItems(), name);
        restaurantRepository.save(restaurant);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void reviseMenu(long id, RestaurantMenu revisedMenu) {
        restaurantRepository.findById(id).map(restaurant -> {
            List<OrderDomainEvent> events = restaurant.reviseMenu(revisedMenu);
            return restaurant;
        }).orElseThrow(RuntimeException::new);
    }

}
