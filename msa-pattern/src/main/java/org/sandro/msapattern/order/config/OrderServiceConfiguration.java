package org.sandro.msapattern.order.config;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.sagas.orchestration.SagaCommandProducer;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.micrometer.core.instrument.MeterRegistry;
import org.sandro.msapattern.order.CancelOrderSagaData;
import org.sandro.msapattern.order.OrderDomainEventPublisher;
import org.sandro.msapattern.order.OrderService;
import org.sandro.msapattern.order.ReviseOrderSagaData;
import org.sandro.msapattern.order.proxy.AccountingServiceProxy;
import org.sandro.msapattern.order.proxy.ConsumerServiceProxy;
import org.sandro.msapattern.order.proxy.KitchenServiceProxy;
import org.sandro.msapattern.order.proxy.OrderServiceProxy;
import org.sandro.msapattern.order.repo.OrderRepository;
import org.sandro.msapattern.order.repo.RestaurantRepository;
import org.sandro.msapattern.order.saga.CancelOrderSaga;
import org.sandro.msapattern.order.saga.CreateOrderSaga;
import org.sandro.msapattern.order.saga.CreateOrderSagaState;
import org.sandro.msapattern.order.saga.ReviseOrderSaga;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@Configuration
@Import({TramEventsPublisherConfiguration.class, SagaOrchestratorConfiguration.class, CommonConfiguration.class})
public class OrderServiceConfiguration {
    // TODO move to framework
    @Bean
    public SagaCommandProducer sagaCommandProducer() {
        // TODO : 이벤트를 보내는 커맨드 프로듀서를 생성
        return (s, s1, list, s2) -> "";
    }

    @Bean
    public OrderService orderService(RestaurantRepository restaurantRepository, OrderRepository orderRepository, DomainEventPublisher eventPublisher,
                                     SagaManager<CreateOrderSagaState> createOrderSagaManager, SagaManager<CancelOrderSagaData> cancelOrderSagaManager,
                                     SagaManager<ReviseOrderSagaData> reviseOrderSagaManager, OrderDomainEventPublisher orderAggregateEventPublisher,
                                     Optional<MeterRegistry> meterRegistry) {
        return new OrderService(orderRepository, restaurantRepository, createOrderSagaManager, cancelOrderSagaManager, reviseOrderSagaManager,
                orderAggregateEventPublisher, meterRegistry, eventPublisher);
    }

    @Bean
    public SagaManager<CreateOrderSagaState> createOrderSagaManager(CreateOrderSaga saga) {
        return new SagaManagerImpl<>(saga, null, null, null, null, null);
    }

    @Bean
    public CreateOrderSaga createOrderSaga(OrderServiceProxy orderService, ConsumerServiceProxy consumerService,
                                           KitchenServiceProxy kitchenServiceProxy, AccountingServiceProxy accountingService) {
        return new CreateOrderSaga(orderService, consumerService, kitchenServiceProxy, accountingService);
    }

    @Bean
    public SagaManager<CancelOrderSagaData> CancelOrderSagaManager(CancelOrderSaga saga) {
        return new SagaManagerImpl<>(saga, null, null, null, null, null);
    }

    @Bean
    public CancelOrderSaga cancelOrderSaga() {
        return new CancelOrderSaga();
    }

    @Bean
    public SagaManager<ReviseOrderSagaData> reviseOrderSagaManager(ReviseOrderSaga saga) {
        return new SagaManagerImpl<>(saga, null, null, null, null, null);
    }

    @Bean
    public ReviseOrderSaga reviseOrderSaga() {
        return new ReviseOrderSaga();
    }


    @Bean
    public KitchenServiceProxy kitchenServiceProxy() {
        return new KitchenServiceProxy();
    }

    @Bean
    public OrderServiceProxy orderServiceProxy() {
        return new OrderServiceProxy();
    }

    @Bean
    public ConsumerServiceProxy consumerServiceProxy() {
        return new ConsumerServiceProxy();
    }

    @Bean
    public AccountingServiceProxy accountingServiceProxy() {
        return new AccountingServiceProxy();
    }

    @Bean
    public OrderDomainEventPublisher orderAggregateEventPublisher(DomainEventPublisher eventPublisher) {
        return new OrderDomainEventPublisher(eventPublisher);
    }

    @Bean
    public MeterRegistryCustomizer<?> meterRegistryCustomizer(@Value("${spring.application.name}") String serviceName) {
        return registry -> registry.config().commonTags("service", serviceName);
    }
}