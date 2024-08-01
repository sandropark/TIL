package org.sandro.msapattern.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MenuItemIdAndQuantity {
    @Setter
    private String menuItemId;
    private final int quantity;
}