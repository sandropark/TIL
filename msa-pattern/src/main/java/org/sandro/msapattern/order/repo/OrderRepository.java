package org.sandro.msapattern.order.repo;

import org.sandro.msapattern.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
