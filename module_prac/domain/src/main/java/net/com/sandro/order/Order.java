package net.com.sandro.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "orders")
@Entity
public class Order {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Float price;
}
