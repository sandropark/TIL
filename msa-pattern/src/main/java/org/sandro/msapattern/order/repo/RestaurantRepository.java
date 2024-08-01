package org.sandro.msapattern.order.repo;

import org.sandro.msapattern.order.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
