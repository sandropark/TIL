package net.com.sandro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.com.sandro.order.Order;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class OrderCreateReq extends ExternalOrderCreateReq {
    public Order toOrder() {
        return Order.builder()
                .price(price)
                .build();
    }
}
