package net.com.sandro.order;

import lombok.RequiredArgsConstructor;
import net.com.sandro.OrderCreateReq;
import net.com.sandro.OrderCreateRes;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderCreateRes create(OrderCreateReq req) {
        Order order = req.toOrder();
        return OrderCreateRes.from(orderRepository.save(order));
    }
}
