package org.sandro.msapattern.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderLineItem {
    private int quantity;
    private String menuItemId;
    private String name;
    @Embedded
    @AttributeOverrides(@AttributeOverride(name = "amount", column = @Column(name = "price")))
    private Money price;

    public Money deltaForChangedQuantity(int newQuantity) {
        return price.multiply(newQuantity - quantity);
    }

    public Money getTotal() {
        return price.multiply(quantity);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}