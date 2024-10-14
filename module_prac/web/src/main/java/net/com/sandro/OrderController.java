package net.com.sandro;

import lombok.RequiredArgsConstructor;
import net.com.sandro.order.OrderService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderCreateRes create(@RequestBody OrderCreateReq req) {
        return orderService.create(req);
    }
}
