package org.sandro.msapattern.order;

import lombok.Builder;

@Builder
public record OrderDetails(Long restaurantId) {
}
