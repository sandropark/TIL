package org.sandro.msapattern.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRevision {
    private Optional<DeliveryInformation> deliveryInformation = Optional.empty();
    private Map<String, Integer> revisedLineItemQuantities;
}