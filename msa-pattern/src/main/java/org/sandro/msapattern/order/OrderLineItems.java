package org.sandro.msapattern.order;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderLineItems {
    @ElementCollection
    @CollectionTable(name = "order_line_items")
    private List<OrderLineItem> lineItems;

    OrderLineItem findOrderLineItem(String lineItemId) {
        return lineItems.stream().filter(li -> li.getMenuItemId().equals(lineItemId)).findFirst().get();
    }

    Money changeToOrderTotal(OrderRevision orderRevision) {
        AtomicReference<Money> delta = new AtomicReference<>(Money.ZERO);

        orderRevision.getRevisedLineItemQuantities().forEach((lineItemId, newQuantity) -> {
            OrderLineItem lineItem = findOrderLineItem(lineItemId);
            delta.set(delta.get().add(lineItem.deltaForChangedQuantity(newQuantity)));
        });
        return delta.get();
    }

    void updateLineItems(OrderRevision orderRevision) {
        getLineItems().stream().forEach(li -> {
            Integer revised = orderRevision.getRevisedLineItemQuantities().get(li.getMenuItemId());
            li.setQuantity(revised);
        });
    }

    Money orderTotal() {
        return lineItems.stream().map(OrderLineItem::getTotal).reduce(Money.ZERO, Money::add);
    }

    LineItemQuantityChange lineItemQuantityChange(OrderRevision orderRevision) {
        Money currentOrderTotal = orderTotal();
        Money delta = changeToOrderTotal(orderRevision);
        Money newOrderTotal = currentOrderTotal.add(delta);
        return new LineItemQuantityChange(currentOrderTotal, newOrderTotal, delta);
    }
}