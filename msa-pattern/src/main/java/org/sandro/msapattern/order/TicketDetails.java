package org.sandro.msapattern.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetails {
    private List<TicketLineItem> lineItems;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}