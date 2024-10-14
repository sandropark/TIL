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
public class OrderCreateRes extends ExternalOrderCreateRes {
    public static OrderCreateRes from(Order order) {
        return OrderCreateRes.builder()
                .price(order.getPrice())
                .build();
    }
}
