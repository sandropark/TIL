package org.sandro.msapattern.order;

import io.eventuate.tram.events.publisher.ResultWithEvents;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "orders")
@Entity
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static ResultWithEvents<Order> createOrder() {
        return null;
    }
}
