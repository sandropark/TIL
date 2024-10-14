package org.sandro.msaprac.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sandro.msaprac.common.entity.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {
    private Long accountId;
    private Integer price;
}
