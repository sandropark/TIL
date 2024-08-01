package org.sandro.msapattern.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sandro.msapattern.order.domain_event.OrderDomainEvent;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_service_restaurants")
@Access(AccessType.FIELD)
public class Restaurant {
    @Id
    private Long id;

    //    @Embedded
    @ElementCollection
    @CollectionTable(name = "order_service_restaurant_menu_items")
    private List<MenuItem> menuItems;
    private String name;

    public List<OrderDomainEvent> reviseMenu(RestaurantMenu revisedMenu) {
        throw new UnsupportedOperationException();
    }

    public void verifyRestaurantDetails(TicketDetails ticketDetails) {
        // TODO - implement me
    }

    public Optional<MenuItem> findMenuItem(String menuItemId) {
        return menuItems.stream().filter(mi -> mi.getId().equals(menuItemId)).findFirst();
    }
}